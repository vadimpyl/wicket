package com.wicket.service;

import com.wicket.model.User;

public interface LoginService
{
    public User login(final String username, final String password);
}
