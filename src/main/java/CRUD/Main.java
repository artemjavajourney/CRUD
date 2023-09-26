package CRUD;

import CRUD.controller.DeveloperController;
import CRUD.controller.SkillController;
import CRUD.controller.TeamController;

import CRUD.model.Developer;
import CRUD.model.Skill;
import CRUD.model.Team;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        testSkill();
        testTeam();
        testDeveloper();
    }

    public static void testSkill() {
        System.out.println("Skill: ");
        SkillController skillController = new SkillController();
        System.out.println(skillController.getById(3l));
        System.out.println("All Skills: " + skillController.getAll());
        System.out.println("New Skill" + skillController.save(new Skill("GO")));
        System.out.println("Adding check: " + skillController.getAll());
        System.out.println("Update Skill: " + skillController.update(3L, new Skill("JS and Python")));
        System.out.println("Delete Skill: ");
        skillController.deleteById(4l);
        System.out.println("Deletion check: " + skillController.getAll());
    }

    public static void testTeam() {
        System.out.println("Team: ");
        TeamController teamController = new TeamController();
        System.out.println(teamController.getById(1l));
        System.out.println("All Teams: " + teamController.getAll());
        System.out.println("New Team" + teamController.save(new Team("Team GO", null)));
        System.out.println("Adding check: " + teamController.getAll());
        System.out.println("Update Team: " + teamController.update(2L, new Team("Team C#,Python", null)));
        System.out.println("Delete Team: ");
        teamController.deleteById(3l);
        System.out.println("Deletion Check: " + teamController.getAll());

    }

    public static void testDeveloper() {
        System.out.println("Developer:  ");
        DeveloperController developerController = new DeveloperController();
        System.out.println(developerController.getById(1l));
        System.out.println("All Developers: " + developerController.getAll());
        System.out.println("New Developer" + developerController.save(new Developer("Sasha", "Novikov",null)));
        System.out.println("Adding check: " + developerController.getAll());
        System.out.println("Update Developer: " + developerController.update(2L, new Developer("Max", "Nemolyakin", null)));
        System.out.println("Delete Developer: ");
        developerController.deleteById(1l);
        System.out.println("Deletion check: " + developerController.getAll());
    }
}