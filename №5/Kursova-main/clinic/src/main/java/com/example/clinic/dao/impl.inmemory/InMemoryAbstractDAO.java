package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.AbstractDAO;
import java.util.*;
import java.util.function.*;

abstract class InMemoryAbstractDAO<T> implements AbstractDAO<T> {
    // For CRUD operations on entities of type <T> in this class
    protected Map<Integer, T> entities;
    protected Function<T, Integer> idGetter;
    protected BiConsumer<T, Integer> idSetter;


    protected InMemoryDatabase database;

    InMemoryAbstractDAO(Map<Integer, T> entities,
                             Function<T, Integer> idGetter,
                             BiConsumer<T, Integer> idSetter,
                             InMemoryDatabase database) {
        this.entities = entities;
        this.idGetter = idGetter;
        this.idSetter = idSetter;
        this.database = database;
    }

    @Override
    public T get(Integer id) {
        return entities.get(id);
    }

    @Override
    public Collection<T> findAll() {
        return entities.values();
    }

    @Override
    public void insert(T entity, boolean generateId) {
        if (generateId) {
            int maxId = entities.keySet().stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0);
            idSetter.accept(entity, maxId + 1);
        }
        entities.put(idGetter.apply(entity), entity);
    }

    @Override
    public void delete(T entity) {
        entities.remove(idGetter.apply(entity));
    }

    @Override
    public void update(T entity) {
        entities.put(idGetter.apply(entity), entity);
    }
}
