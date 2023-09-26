package CRUD.model;

import java.util.List;
import java.util.StringJoiner;

public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;
    private Status status;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Developer(String firstName, String lastName, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("skills=" + skills)
                .toString();
    }
}
