package CRUD.repository.jsonClassesImpl;

import CRUD.model.Team;
import CRUD.model.Status;
import CRUD.repository.TeamRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class JsonTeamRepositoryImpl implements TeamRepository {
    private static final Path PATH = Paths.get("src\\main\\resources\\team.json");
    private static final Gson GSON = new Gson();

    @Override
    public Team getById(Long aLong) {
        if (aLong == null || aLong <= 0) {
            throw new IllegalArgumentException("Invalid Id.");
        }
        return getTeamFromJson().stream()
                .filter(team -> team.getId().equals(aLong))
                .findFirst().orElse(null);
    }

    @Override
    public List<Team> getAll() {
        return getTeamFromJson();
    }

    @Override
    public Team save(Team team) {
        if (team.getName() == null) {
            return null;
        }
        List<Team> teams = getTeamFromJson();
        team.setId(generateMaxId(teams));
        team.setTeamStatus(Status.ACTIVE);
        teams.add(team);

        writeFile(GSON.toJson(teams));
        return team;
    }

    @Override
    public Team update(Long aLong, Team team) {
        List<Team> teams = getTeamFromJson();
        boolean isUpdate = false;
        for (Team t : teams) {
            if (t.getId().equals(aLong)) {
                t.setName(team.getName());
                writeFile(GSON.toJson(teams));
                isUpdate = true;
                break;
            }
        }
        if (!isUpdate) save(team);
        return team;
    }

    @Override
    public void deleteById(Long asLong) {
        List<Team> teams = getTeamFromJson();

        for (Team team : teams) {
            if (team.getId().equals(asLong)) {
                team.setTeamStatus(Status.DELETED);
                writeFile(GSON.toJson(teams));
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

    private void writeFile(String teamObj) {
        try {
            Files.writeString(PATH, teamObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Team> getTeamFromJson() {
        Type targetType = new TypeToken<ArrayList<Team>>() {
        }.getType();
        return new Gson().fromJson(readFile(), targetType);
    }

    private Long generateMaxId(List<Team> teams) {
        return teams.stream().mapToLong(Team::getId).max().getAsLong() + 1;
    }

}
