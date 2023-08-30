package com.service.ipml;

import com.model.Zone;
import com.repository.IZoneRepository;
import com.service.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements IZoneService {
    @Autowired
    IZoneRepository iZoneRepository;

    @Override
    public List<Zone> getAll() {
        return null;
    }

    @Override
    public Zone getById(long id) {
        return null;
    }

    @Override
    public Zone create(Zone zone) {
        return null;
    }

    @Override
    public Zone edit(Zone zone) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
