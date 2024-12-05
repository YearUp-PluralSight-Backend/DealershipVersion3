package com.pluralsight.dealershipversion2.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDOperation<T> {

    default boolean create(T t) {
        System.out.println("Create operation");
    }

    default List<T> read() {
        System.out.println("Read operation");
    }

    default Optional<T> read(int id) {
        System.out.println("Read operation");
    }

    default boolean update(int id, T t) {
        System.out.println("Update operation");
    }

    default boolean delete(int id) {
        System.out.println("Delete operation");
    }



}
