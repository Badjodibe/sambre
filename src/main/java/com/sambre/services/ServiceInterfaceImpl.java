package com.sambre.services;

import java.util.List;
import java.util.Optional;

public interface ServiceInterfaceImpl<T> {
    Optional<T> save(T t);
    List<T> getAll();
    Optional<T> findById(String id);

    Optional<T> update(String id, T t);
    void delete(String id);
}