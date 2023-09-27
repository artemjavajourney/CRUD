package CRUD.repository.jsonClassesImpl;

import CRUD.auxiliary.Paths;
import CRUD.auxiliary.Utils;
import CRUD.model.Team;
import CRUD.model.Status;
import CRUD.repository.TeamRepository;

import java.util.List;

public class JsonTeamRepositoryImpl implements TeamRepository {

    @Override
    public Team getById(Long aLong) {
        if (aLong == null || aLong <= 0) {
            throw new IllegalArgumentException("Invalid Id.");
        }
        return Utils.getDataFromJson(Paths.TEAM, Team.class).stream()
                .filter(team -> team.getId().equals(aLong))
                .findFirst().orElse(null);
    }

    @Override
    public List<Team> getAll() {
        return Utils.getDataFromJson(Paths.TEAM, Team.class);
    }

    @Override
    public Team save(Team team) {
        if (team.getName() == null) {
            return null;
        }
        List<Team> teams = Utils.getDataFromJson(Paths.TEAM, Team.class);
        team.setId(Utils.generateMaxId(teams));
        team.setStatus(Status.ACTIVE);
        teams.add(team);

        Utils.writeFile(Paths.TEAM, teams);
        return team;
    }

    @Override
    public Team update(Long aLong, Team team) {
        List<Team> teams = Utils.getDataFromJson(Paths.TEAM, Team.class);
        teams.stream()
                .filter(t -> t.getId().equals(aLong))
                .findFirst()
                .ifPresentOrElse(t -> {
                    t.setName(team.getName());
                    Utils.writeFile(Paths.TEAM, teams);
                }, () -> save(team));
        return team;
    }

    @Override
    public void deleteById(Long asLong) {
        List<Team> teams = Utils.getDataFromJson(Paths.TEAM, Team.class);
        teams.stream()
                .filter(team -> team.getId().equals(asLong))
                .findFirst()
                .ifPresent(team -> {
                    team.setStatus(Status.DELETED);
                    Utils.writeFile(Paths.TEAM, teams);
                });
    }
}
