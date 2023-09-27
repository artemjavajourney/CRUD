package CRUD.auxiliary;

import CRUD.model.Default;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;

import com.google.gson.reflect.TypeToken;

import java.nio.file.Path;
import java.util.List;

public class Utils {

    private static final Gson GSON = new Gson();

    /**
     * Читает содержимое файла по указанному пути и возвращает его в виде строки.
     *
     * @param paths Путь к файлу.
     * @return Содержимое файла в виде строки.
     */
    public static String readFile(Paths paths) {
        try {
            return Files.readString(Path.of(paths.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записывает список объектов в JSON-файл по указанному пути.
     *
     * @param paths Путь к файлу, в который нужно записать данные.
     * @param list  Список объектов, которые нужно записать.
     */
    public static <T extends Default> void writeFile(Paths paths, List<T> list) {
        try {
            Files.writeString(Path.of(paths.getPath()), GSON.toJson(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Генерирует максимальный идентификатор для списка объектов.
     *
     * @param list Список объектов.
     * @return Максимальный идентификатор плюс один.
     */
    public static <T extends Default> Long generateMaxId(List<T> list) {
        return list.stream().mapToLong(T::getId).max().getAsLong() + 1;
    }

    /**
     * Читает данные из JSON-файла и преобразует их в список объектов указанного класса.
     *
     * @param paths Путь к файлу с данными.
     * @param clazz Класс объектов, в которые нужно преобразовать данные.
     * @return Список объектов.
     */
    public static <T extends Default> List<T> getDataFromJson(Paths paths, Class<T> clazz) {
        String jsonContent = readFile(paths); // Читаем содержимое файла

        Type targetClassType = TypeToken.getParameterized(List.class, clazz).getType();
        return GSON.fromJson(jsonContent, targetClassType);
    }
}
