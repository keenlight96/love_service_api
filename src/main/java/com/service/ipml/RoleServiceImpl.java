package com.service.ipml;

import com.model.Comment;
import com.model.Role;
import com.repository.IRoleRepository;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Role> role = iRoleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        } else {
            return null;
        }
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
