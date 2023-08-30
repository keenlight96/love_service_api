package com.service;

import java.util.List;

public interface ICrudService<E> {
    List<E> getAll();
    E getById(long id);
    E create(E e);
    E edit (E e);
    void deleteById(int id);
}
