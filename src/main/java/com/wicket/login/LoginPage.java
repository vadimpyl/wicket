package com.wicket.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LoginPage extends WebPage
{
    public LoginPage(PageParameters parameters)
    {
        super(parameters);
        add(new LoginForm("loginForm"));
    }
}
