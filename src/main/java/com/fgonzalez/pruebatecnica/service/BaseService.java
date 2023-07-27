package com.fgonzalez.pruebatecnica.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    Optional<T> findById(Integer id);

    Optional<List<T>> findAll();

    Optional<T> addNew(T registro) throws IllegalArgumentException;

    void remove(T registro) throws IllegalArgumentException;

    Optional<T> update(T registro) throws IllegalArgumentException;

    Optional<T> save(T registro) throws IllegalArgumentException;
}
