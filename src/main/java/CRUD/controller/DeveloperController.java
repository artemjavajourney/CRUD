package CRUD.controller;

import CRUD.model.Developer;
import CRUD.repository.DeveloperRepository;
import CRUD.repository.jsonClassesImpl.JsonDeveloperRepoImpl;

import java.util.List;

/**
 * Класс, управляющий операциями с объектами разработчика.
 */
public class DeveloperController {

    DeveloperRepository developerRepository = new JsonDeveloperRepoImpl();

    /**
     * Получает объект разработчика по указанному идентификатору.
     *
     * @param aLong Идентификатор разработчика.
     * @return Объект разработчика или null, если разработчик не найден.
     */
    public Developer getById(Long aLong) {
        return developerRepository.getById(aLong);
    }

    /**
     * Получает список всех разработчиков.
     *
     * @return Список всех разработчиков.
     */
    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    /**
     * Сохраняет нового разработчика.
     *
     * @param t Объект разработчика для сохранения.
     * @return Сохраненный объект разработчика.
     */
    public Developer save(Developer t) {
        return developerRepository.save(t);
    }

    /**
     * Обновляет информацию о разработчике с указанным идентификатором.
     *
     * @param aLong        Идентификатор разработчика, который нужно обновить.
     * @param t Обновленные данные разработчика.
     * @return Обновленный объект разработчика, либо новый объект разработчика если id не найден.
     */
    public Developer update(Long aLong, Developer t) {
        return developerRepository.update(aLong, t);
    }

    /**
     * Удаляет разработчика по указанному идентификатору.
     *
     * @param id Идентификатор разработчика, который нужно удалить.
     */
    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }
}
