package com.example.bootstudy.dao;

import com.example.bootstudy.model.User;

import java.util.List;

public interface UserDao {


    void save(User user);
    List<User> getAll();
    User getUserById(Long id);
    void  update(User user);
    void deleteById(long id);

    User getUserByName(String name);





}
