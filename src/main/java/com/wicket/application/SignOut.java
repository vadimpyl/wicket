package com.wicket.application;

import com.wicket.login.SignIn;
import com.wicket.register.Register;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignOut extends WebPage
{
    /**
     * Constructor
     *
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SignOut(final PageParameters parameters)
    {
        add(new BookmarkablePageLink<>("signin", SignIn.class));

        getSession().invalidate();
    }
}