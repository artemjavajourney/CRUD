package CRUD.repository.jsonClassesImpl;


import CRUD.model.Developer;
import CRUD.model.Status;
import CRUD.repository.DeveloperRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonDeveloperRepoImpl implements DeveloperRepository {
    private static final Path PATH = Paths.get("src\\main\\resources\\developer.json");
    private static final Gson GSON = new Gson();

    @Override
    public Developer getById(Long aLong) {
        if (aLong == null || aLong <= 0) {
            throw new IllegalArgumentException("Invalid Id.");
        }

        return getDeveloperFromJson().stream()
                .filter(dev -> dev.getId().equals(aLong))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return getDeveloperFromJson();
    }

    @Override
    public Developer save(Developer developer) {
        if (developer.getFirstName() == null && developer.getLastName() == null) {
            return null;
        }

        List<Developer> devs = getDeveloperFromJson();
        developer.setId(generateMaxId(devs));
        developer.setStatus(Status.ACTIVE);
        devs.add(developer);

        writeFile(GSON.toJson(devs));
        return developer;

    }

    @Override
    public Developer update(Long aLong, Developer developer) {
        List<Developer> devs = getDeveloperFromJson();
        boolean isUpdate = false;
        for (Developer dev : devs) {
            if (dev.getId().equals(aLong)) {
                dev.setFirstName(developer.getFirstName());
                dev.setLastName(developer.getLastName());
                writeFile(GSON.toJson(devs));
                isUpdate = true;
                break;
            }
        }
        if (!isUpdate) save(developer);

        return developer;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Developer> devs = getDeveloperFromJson();

        for (Developer dev : devs) {
            if (dev.getId().equals(aLong)) {
                dev.setStatus(Status.DELETED);
                writeFile(GSON.toJson(devs));
                return;
            }
        }
    }

    private String readFile() {
        try {
            return Files.readString(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(String devObj) {
        try {
            Files.writeString(PATH, devObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Developer> getDeveloperFromJson() {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        return new Gson().fromJson(readFile(), targetClassType);
    }

    private Long generateMaxId(List<Developer> dev) {
        return dev.stream().mapToLong(Developer::getId).max().getAsLong() + 1;
    }

}
