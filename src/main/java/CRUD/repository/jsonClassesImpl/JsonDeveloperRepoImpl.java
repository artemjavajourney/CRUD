package CRUD.repository.jsonClassesImpl;

import CRUD.auxiliary.Paths;
import CRUD.auxiliary.Utils;
import CRUD.model.Developer;
import CRUD.model.Status;
import CRUD.repository.DeveloperRepository;

import java.util.List;

/**
 * Реализация репозитория разработчиков с использованием JSON-файла.
 */
public class JsonDeveloperRepoImpl implements DeveloperRepository {

    @Override
    public Developer getById(Long aLong) {
        if (aLong == null || aLong <= 0) {
            throw new IllegalArgumentException("Invalid Id.");
        }

        return Utils.getDataFromJson(Paths.DEVELOPER, Developer.class).stream()
                .filter(dev -> dev.getId().equals(aLong))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return Utils.getDataFromJson(Paths.DEVELOPER, Developer.class);
    }

    @Override
    public Developer save(Developer developer) {
        if (developer.getFirstName() == null && developer.getLastName() == null) {
            return null;
        }

        List<Developer> devs = Utils.getDataFromJson(Paths.DEVELOPER, Developer.class);
        developer.setId(Utils.generateMaxId(devs));
        developer.setStatus(Status.ACTIVE);
        devs.add(developer);

        Utils.writeFile(Paths.DEVELOPER, devs);
        return developer;

    }

    @Override
    public Developer update(Long aLong, Developer developer) {
        List<Developer> devs = Utils.getDataFromJson(Paths.DEVELOPER, Developer.class);
        devs.stream()
                .filter(dev -> dev.getId().equals(aLong))
                .findFirst()
                .ifPresentOrElse(dev -> {
                    dev.setFirstName(developer.getFirstName());
                    dev.setLastName(developer.getLastName());
                    Utils.writeFile(Paths.DEVELOPER, devs);
                }, () -> save(developer));
        return developer;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Developer> devs = Utils.getDataFromJson(Paths.DEVELOPER, Developer.class);
        devs.stream()
                .filter(developer -> developer.getId().equals(aLong))
                .findFirst()
                .ifPresent(developer -> {
                    developer.setStatus(Status.DELETED);
                    Utils.writeFile(Paths.DEVELOPER, devs);
                });
    }
}
