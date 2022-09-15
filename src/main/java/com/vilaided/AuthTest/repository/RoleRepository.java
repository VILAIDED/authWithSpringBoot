package com.vilaided.AuthTest.repository;

import com.vilaided.AuthTest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {
    Role findByName(String name);


}
