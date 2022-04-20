package com.example.bootstudy.dao;

import com.example.bootstudy.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao  {

    void save(Role role);
    List<Role> getAllRoles();
    Role getRoleById(long id);
    void  update(Role role);
    void deleteById(long id);

    Role getAuthorityByName(String name);


}
