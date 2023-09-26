package CRUD.controller;

import CRUD.model.Skill;
import CRUD.repository.jsonClassesImpl.JsonSkillRepositoryImpl;
import CRUD.repository.SkillRepository;

import java.util.List;

public class SkillController {
    private final SkillRepository skillRepository = new JsonSkillRepositoryImpl();

    public Skill getById(Long aLong) {
        return skillRepository.getById(aLong);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill save(Skill t) {
        return skillRepository.save(t);
    }

    public Skill update(Long aLong, Skill t) {
        return skillRepository.update(aLong, t);
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
