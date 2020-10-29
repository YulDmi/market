package com.geekbrain.market.services;

import com.geekbrain.market.entities.Role;
import com.geekbrain.market.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findByName(String name) {return  roleRepository.findByName(name);}


    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
