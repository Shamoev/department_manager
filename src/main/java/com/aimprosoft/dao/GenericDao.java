package com.aimprosoft.dao;

import java.util.List;

public interface GenericDao<T> {

    int create(T objectToCreate);

    T read(int id);

    void update(T objectToUpdate);

    void delete(int id);

    List<T> listOfAll();
}
