package com.service;

import com.model.Account;
import com.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

public interface ICrudService<E> {
    List<E> getAll();
    E getById(long id);
    E create(E e);
    E edit (E e);
    void deleteById(long id);

}
