package app.repository;

import java.util.List;

public interface GenericRepository<E, K> {

    E save(E entity);

    List<E> findAll();
}
