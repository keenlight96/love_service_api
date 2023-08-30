package com.service.ipml;

import com.model.Comment;
import com.model.Zone;
import com.repository.IZoneRepository;
import com.service.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneServiceImpl implements IZoneService {
    @Autowired
    IZoneRepository iZoneRepository;

    @Override
    public List<Zone> getAll() {
        return iZoneRepository.findAll();
    }

    @Override
    public Zone getById(long id) {
        Optional<Zone> zone = iZoneRepository.findById(id);
        if (zone.isPresent()) {
            return zone.get();
        } else {
            return null;
        }
    }

    @Override
    public Zone create(Zone zone) {
        return iZoneRepository.save(zone);
    }

    @Override
    public Zone edit(Zone zone) {
        return iZoneRepository.save(zone);
    }

    @Override
    public void deleteById(long id) {
        iZoneRepository.deleteById(id);
    }
}
