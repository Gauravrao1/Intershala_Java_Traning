package hospitality.repository;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
        T create(T entity);

        Optional<T> findById(int id);

        List<T> findAll();

        T update(T entity);

        boolean delete(int id);
}
