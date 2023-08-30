package com.service.ipml;

import com.model.Role;
import com.repository.IRoleRepository;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository iRoleRepository;

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role getById(long id) {
        return null;
    }

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Role edit(Role role) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
