package com.wicket.helper;

import com.wicket.service.LoginService;
import com.wicket.service.LoginServiceImpl;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public final class SignInSession extends AuthenticatedWebSession
{
    /** Trivial user representation */
    private String user;

    /**
     * Constructor
     *
     * @param request
     */
    public SignInSession(Request request)
    {
        super(request);
    }

    /**
     * Checks the given username and password, returning a User object if if the username and
     * password identify a valid user.
     *
     * @param username
     *            The username
     * @param password
     *            The password
     * @return True if the user was authenticated
     */
    @Override
    public final boolean authenticate(final String username, final String password)
    {
        final String WICKET = "wicket";
        LoginService loginService = new LoginServiceImpl();
        if (user == null)
        {
            // Trivial password "db"
            if (loginService.login(username, password) != null)
            {
                user = username;
            }
        }

        return user != null;
    }

    /**
     * @return User
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user
     *            New user
     */
    public void setUser(final String user)
    {
        this.user = user;
    }

    @Override
    public Roles getRoles()
    {
        return null;
    }
}