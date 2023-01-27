package com.example.bootstudy.service;

import com.example.bootstudy.dao.UserDao;
import com.example.bootstudy.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value="userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

   public UserServiceImpl(UserDao userDao,PasswordEncoder passwordEncoder) {
       this.userDao = userDao;
       this.passwordEncoder = passwordEncoder;
   }



    @Override
    public void saveUser(User user) {
        System.out.println("************SAVING PROCESS********************************");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        System.out.println("UserID:" + user.getId() +
                "with rolename was saved");

    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getUserById(Long id) {
        return  userDao.getUserById(id);
    }


    @Override
    public void update(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);

    }


    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteUser(User user) {
       userDao.delete(user);
    }



}