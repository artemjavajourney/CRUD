package CRUD.repository.jsonClassesImpl;

import CRUD.auxiliary.Paths;
import CRUD.auxiliary.Utils;
import CRUD.model.Skill;
import CRUD.model.Status;
import CRUD.repository.SkillRepository;

import java.util.List;


public class JsonSkillRepositoryImpl implements SkillRepository {

    @Override
    public Skill getById(Long aLong) {
        if (aLong == null || aLong <= 0) {
            throw new IllegalArgumentException("Invalid Id.");
        }
        return Utils.getDataFromJson(Paths.SKILL, Skill.class).stream()
                .filter(sk -> sk.getId().equals(aLong))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return Utils.getDataFromJson(Paths.SKILL, Skill.class);
    }

    @Override
    public Skill save(Skill skill) {
        if (skill.getName() == null) {
            return null;
        }
        List<Skill> skills = Utils.getDataFromJson(Paths.SKILL, Skill.class);
        skill.setId(Utils.generateMaxId(skills));
        skill.setStatus(Status.ACTIVE);
        skills.add(skill);

        Utils.writeFile(Paths.SKILL, skills);
        return skill;
    }

    @Override
    public Skill update(Long aLong, Skill skill) {
        List<Skill> skills = Utils.getDataFromJson(Paths.SKILL, Skill.class);
        skills.stream()
                .filter(s -> s.getId().equals(aLong))
                .findFirst()
                .ifPresentOrElse(s -> {
                    s.setName(skill.getName());
                    Utils.writeFile(Paths.SKILL, skills);
                }, () -> save(skill));
        return skill;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Skill> skills = Utils.getDataFromJson(Paths.SKILL, Skill.class);
        skills.stream()
                .filter(skill -> skill.getId().equals(aLong))
                .findFirst()
                .ifPresent(skill -> {
                    skill.setStatus(Status.DELETED);
                    Utils.writeFile(Paths.SKILL, skills);
                });
    }
}
