package com.wicket.login;

import com.wicket.service.LoginService;
import com.wicket.service.LoginServiceImpl;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginForm extends Form<LoginForm>
{
    LoginService loginService = new LoginServiceImpl();

    private String username;
    private String password;
    private String loginStatus;

    public LoginForm(String id)
    {
        super(id);
        setDefaultModel(new CompoundPropertyModel(this));

        add(new TextField("username"));
        add(new PasswordTextField("password"));
        add(new Label("loginStatus"));
    }



    public final void onSubmit()
    {
        if(loginService.login(username, password) != null)
        {
            loginStatus = "Wrong username or password !";
        }
        else
            {
            loginStatus = "Wrong username or password !";
        }
    }
}
