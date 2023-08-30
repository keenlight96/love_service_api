package com.service.ipml;

import com.model.Role;
import com.repository.IRoleRepository;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository iRoleRepository;

    @Override
    public List<Role> getAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Role getById(long id) {
        return iRoleRepository.findById(id).get();
    }
    public Role findByName(String name){
        return  iRoleRepository.findByNameRole(name);
    }

    @Override
    public Role create(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public Role edit(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public void deleteById(long id) {
        iRoleRepository.deleteById(id);

    }
}
