package hospitality.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;

public class InMemoryCrudDao<T> implements CrudDao<T> {
        private final Map<Integer, T> records = new LinkedHashMap<>();
        private final ToIntFunction<T> idReader;
        private final ObjIntConsumer<T> idWriter;
        private int nextId = 1;

        public InMemoryCrudDao(ToIntFunction<T> idReader, ObjIntConsumer<T> idWriter) {
                this.idReader = idReader;
                this.idWriter = idWriter;
        }

        @Override
        public synchronized T create(T entity) {
                int id = idReader.applyAsInt(entity);
                if (id <= 0) {
                        id = nextId++;
                        idWriter.accept(entity, id);
                } else {
                        nextId = Math.max(nextId, id + 1);
                }
                records.put(idReader.applyAsInt(entity), entity);
                return entity;
        }

        @Override
        public synchronized Optional<T> findById(int id) {
                return Optional.ofNullable(records.get(id));
        }

        @Override
        public synchronized List<T> findAll() {
                return new ArrayList<>(records.values());
        }

        @Override
        public synchronized T update(T entity) {
                int id = idReader.applyAsInt(entity);
                if (!records.containsKey(id))
                        throw new IllegalArgumentException("Record not found: " + id);
                records.put(id, entity);
                return entity;
        }

        @Override
        public synchronized boolean delete(int id) {
                return records.remove(id) != null;
        }
}
