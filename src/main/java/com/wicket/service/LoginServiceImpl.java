package com.wicket.service;


import com.wicket.helper.UserHelper;
import com.wicket.model.User;

public class LoginServiceImpl implements LoginService
{

    @Override
    public User login(final String username, final String password)
    {

        return UserHelper.listOfUsers().stream().
                filter(user -> username.equals(user.getLogin()) && password.equals(user.getPassword())).findAny().orElse(null);
    }
}