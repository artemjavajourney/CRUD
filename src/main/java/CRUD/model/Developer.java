package CRUD.model;

import java.util.List;
import java.util.StringJoiner;

/**
 * Класс, представляющий объект разработчика.
 */
public class Developer extends Default {

    private String firstName;
    private String lastName;
    private List<Skill> skills;

    public Developer(String firstName, String lastName, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Developer.class.getSimpleName() + "[", "]")
                .add("id=" + getId() + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("skills='" + skills + "'")
                .add("status='" + getStatus())
                .toString();
    }
}
