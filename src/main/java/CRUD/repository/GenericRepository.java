package CRUD.repository;

import java.util.List;

public interface GenericRepository<T, Long> {
    T getById(Long aLong);

    List<T> getAll();

    T save(T t);

    T update(Long aLong, T t);

    void deleteById(Long aLong);
}
