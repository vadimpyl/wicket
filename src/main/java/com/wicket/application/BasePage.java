package com.wicket.application;

import com.wicket.model.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class BasePage extends WebPage
{
    private User selected;

    /**
     * Constructor
     */
    public BasePage()
    {
        add(new Label("selectedLabel", new PropertyModel<>(this, "selectedContactLabel")));
        add(new FeedbackPanel("feedback"));
    }

    /**
     * @return string representation of selected contact property
     */
    public String getSelectedContactLabel()
    {
        if (selected == null)
        {
            return "No Contact Selected";
        }
        else
        {
            return selected.getFirstName() + " " + selected.getLastName();
        }
    }

    class ActionPanel extends Panel
    {
        /**
         * @param id
         *            component id
         * @param model
         *            model for contact
         */
        public ActionPanel(String id, IModel<User> model)
        {
            super(id, model);
            add(new Link<Void>("select")
            {
                @Override
                public void onClick()
                {
                    selected = (User)getParent().getDefaultModelObject();
                }
            });
        }
    }

    /**
     * @return selected contact
     */
    public User getSelected()
    {
        return selected;
    }

    /**
     * sets selected contact
     *
     * @param selected
     */
    public void setSelected(User selected)
    {
        addStateChange();
        this.selected = selected;
    }
}
