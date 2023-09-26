package CRUD.controller;

import CRUD.model.Developer;
import CRUD.model.Skill;
import CRUD.repository.DeveloperRepository;
import CRUD.repository.jsonClassesImpl.JsonDeveloperRepoImpl;

import java.util.List;

public class DeveloperController {
    DeveloperRepository developerRepository = new JsonDeveloperRepoImpl();

    public Developer getById(Long aLong) {
        return developerRepository.getById(aLong);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Developer save(Developer t) {
        return developerRepository.save(t);
    }

    public Developer update(Long aLong, Developer t) {
        return developerRepository.update(aLong, t);
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }
}
