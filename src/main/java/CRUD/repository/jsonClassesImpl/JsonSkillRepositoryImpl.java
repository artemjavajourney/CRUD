package CRUD.repository.jsonClassesImpl;

import CRUD.model.Skill;
import CRUD.model.Status;
import CRUD.repository.SkillRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class JsonSkillRepositoryImpl implements SkillRepository {

    private static final Path PATH = Paths.get("src\\main\\resources\\skill.json");
    private static final Gson GSON = new Gson();

    @Override
    public Skill getById(Long aLong) {
        if (aLong == null || aLong <= 0) {
            throw new IllegalArgumentException("Invalid Id.");
        }
        return getSkillsFromJson().stream()
                .filter(sk -> sk.getId().equals(aLong))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return getSkillsFromJson();
    }

    @Override
    public Skill save(Skill skill) {
        if (skill.getName() == null) {
            return null;
        }
        List<Skill> skills = getSkillsFromJson();
        skill.setId(generateMaxId(skills));
        skill.setStatus(Status.ACTIVE);
        skills.add(skill);

        writeFile(GSON.toJson(skills));
        return skill;
    }

    @Override
    public Skill update(Long aLong, Skill skill) {
        List<Skill> skills = getSkillsFromJson();
        boolean isUpdate = false;
        for (Skill sk : skills) {
            if (sk.getId().equals(aLong)) {
                sk.setName(skill.getName());
                writeFile(GSON.toJson(skills));
                isUpdate = true;
                break;
            }
        }
        if (!isUpdate) save(skill);

        return skill;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Skill> skills = getSkillsFromJson();

        for (Skill s : skills) {
            if (s.getId().equals(aLong)) {
                s.setStatus(Status.DELETED);
                writeFile(GSON.toJson(skills));
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

    private void writeFile(String skillObj) {
        try {
            Files.writeString(PATH, skillObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Skill> getSkillsFromJson() {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        return new Gson().fromJson(readFile(), targetClassType);
    }

    public Long generateMaxId(List<Skill> list) {
        return list.stream().mapToLong(Skill::getId).max().getAsLong() + 1;
    }
}
