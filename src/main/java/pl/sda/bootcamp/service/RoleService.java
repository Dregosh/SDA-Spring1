package pl.sda.bootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Role;
import pl.sda.bootcamp.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleName(String name) {
        return this.roleRepository.findByRoleName(name);
    }
}
