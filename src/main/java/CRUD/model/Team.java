package CRUD.model;

import java.util.List;
import java.util.StringJoiner;

public class Team {
    private Long id;
    private String name;
    private List<Developer> developers;
    private Status teamStatus;


    public Team(String name, List<Developer> developers) {
        this.name = name;
        this.developers = developers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Status getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Status teamStatus) {
        this.teamStatus = teamStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Team.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("developers=" + developers)
                .toString();
    }
}
