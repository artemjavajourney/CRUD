package CRUD.model;

import java.util.List;
import java.util.StringJoiner;

/**
 * Класс, представляющий команду разработчиков.
 */
public class Team extends Default {

    private String name;
    private List<Developer> developers;


    public Team(String name, List<Developer> developers) {
        this.name = name;
        this.developers = developers;
    }

    public String getName() {
        return name;
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
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("developers=" + developers + "'")
                .add("status=" + getStatus())
                .toString();
    }
}
