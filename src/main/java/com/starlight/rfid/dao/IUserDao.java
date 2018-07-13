package com.starlight.rfid.dao;

import com.starlight.rfid.model.User;
import java.util.List;

public interface IUserDao {
    public List<User> queryUserByName(String name);
    public User queryUserById(int id);
    public void addUser();
    public void delUser(int id);
    public void alterUserById(int id);
}
