package CRUD.model;

/**
 * Базовый абстрактный класс для объектов с идентификатором и статусом.
 */
public abstract class Default {

    private Long id;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
