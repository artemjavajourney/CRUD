package CRUD.model;

import java.util.StringJoiner;

public class Skill {
    private Long id;
    private String name;
    private Status status;

    public Skill(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Skill.class.getSimpleName() + ": [", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
