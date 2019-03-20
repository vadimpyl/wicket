package com.wicket.helper;import org.apache.wicket.markup.html.form.SubmitLink;


import com.wicket.model.User;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ActionPanel extends Panel
{
    public ActionPanel(String id, IModel<User> model)
    {
        super(id, model);

        add(new Link<Void>("DELETE")
        {
            @Override
            public void onClick()
            {
                User user = (User)ActionPanel.this.getDefaultModelObject();
                UserHelper.listOfUsers().remove(user);
            }
        });
    }
}
