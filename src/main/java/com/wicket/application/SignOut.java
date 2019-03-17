package com.wicket.application;

import org.apache.wicket.markup.html.WebPage;
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
        getSession().invalidate();
    }
}