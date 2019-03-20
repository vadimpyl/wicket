package com.wicket.application;

import com.wicket.helper.AuthenticatedWebPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import org.apache.wicket.model.PropertyModel;


public class Welcome extends WebPage implements AuthenticatedWebPage
{

    public Welcome()
    {
        add(new Label("userName", new PropertyModel<>(this, "session.user")));
        add(new BookmarkablePageLink<>("filter", FilterTable.class));
        add(new BookmarkablePageLink<>("edit", EditTable.class));

    }
}