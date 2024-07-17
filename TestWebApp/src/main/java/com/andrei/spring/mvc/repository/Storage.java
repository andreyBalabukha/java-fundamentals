package com.andrei.spring.mvc.repository;

import java.util.HashMap;
import java.util.Map;

public class Storage<T> { // {1: value<T>}

    protected Map<Long, T> storage = new HashMap<>();

    public Storage(Map<Long, T> storage) {
        this.storage = storage;
    }

    public Map<Long, T> getStorage() {
        return storage;
    }
}
