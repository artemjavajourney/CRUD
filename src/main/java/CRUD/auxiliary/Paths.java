package CRUD.auxiliary;

/**
 * Перечисление, представляющее пути к файлам JSON.
 */
public enum Paths {

    DEVELOPER("src\\main\\resources\\developer.json"),
    SKILL("src\\main\\resources\\skill.json"),
    TEAM("src\\main\\resources\\team.json");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
