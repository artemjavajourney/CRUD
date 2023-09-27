package CRUD.controller;

import CRUD.model.Team;
import CRUD.repository.TeamRepository;
import CRUD.repository.jsonClassesImpl.JsonTeamRepositoryImpl;

import java.util.List;

public class TeamController {

    TeamRepository teamRepository = new JsonTeamRepositoryImpl();

    public Team getById(Long aLong) {
        return teamRepository.getById(aLong);
    }

    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    public Team save(Team t) {
        return teamRepository.save(t);
    }

    public Team update(Long aLong, Team t) {
        return teamRepository.update(aLong, t);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}

