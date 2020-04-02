package com.example.studentmeetup.database.dao;

import com.example.studentmeetup.model.Report;
import com.example.studentmeetup.model.User;

public interface usersDAO {
    public User getUser();
    public void createUser(User user);
    public void editUser(User user);
    public void reportUser(User user, Report form);

}
