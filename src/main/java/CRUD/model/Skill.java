package CRUD.model;

import java.util.StringJoiner;

/**
 * Этот класс представляет объект Навыка.
 */
public class Skill extends Default {

    private String name;

    public Skill(String name) {
        this.name = name;
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
                .add("id=" + getId() + "'")
                .add("name='" + name + "'")
                .add("status='" + getStatus())
                .toString();
    }
}
