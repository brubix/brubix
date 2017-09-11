package com.brubix.entityservice.service;


import com.brubix.entityservice.entity.Dummy;
import com.brubix.entityservice.repository.DummyRepository;

public class DummyServiceImpl implements DummyService {

    private DummyRepository dummyRepository;

    public DummyServiceImpl(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    @Override
    public Dummy create(String data) {
        Dummy dummy = new Dummy();
        dummy.setData(data);
        return dummyRepository.save(dummy);
    }

    @Override
    public Dummy get(Long id) {
        return dummyRepository.getOne(id);
    }
}
