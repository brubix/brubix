package com.brubix.entityservice.service;


import com.brubix.model.Dummy;

public interface DummyService {

    Dummy create(String data);

    Dummy get(Long id);
}
