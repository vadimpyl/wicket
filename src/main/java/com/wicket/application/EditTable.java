package com.wicket.application;

import com.wicket.helper.AuthenticatedWebPage;
import com.wicket.helper.DetachableUserModel;
import com.wicket.helper.UserHelper;
import com.wicket.model.User;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Iterator;

public class EditTable extends BasePage
{
    final Form<?> form;

    public EditTable()
    {
        form = new Form("form");
        add(new Label("userName", new PropertyModel<>(this, "session.user")));
        add(form);

        RefreshingView<User> refreshingView = new RefreshingView<User>("simple")
        {
            @Override
            protected Iterator<IModel<User>> getItemModels()
            {
                SortParam<String> sort = new SortParam<>("firstName", true);
                Iterator<User> contacts = UserHelper.listOfUsers().iterator();

                return new ModelIteratorAdapter<User>(contacts)
                {

                    @Override
                    protected IModel<User> model(User object)
                    {
                        return new CompoundPropertyModel<>(
                                new DetachableUserModel(object));
                    }
                };
            }

            @Override
            protected void populateItem(final Item<User> item)
            {
                // populate the row of the repeater
                IModel<User> contact = item.getModel();
                item.add(new ActionPanel("actions", contact));
                item.add(new TextField<>("login"));
                item.add(new TextField<>("firstName"));
                item.add(new TextField<>("lastName"));
                item.add(new TextField<>("birthCity"));
                item.add(new TextField<>("birthCountry"));
            }

            @Override
            protected Item<User> newItem(String id, int index, IModel<User> model)
            {
                // this item sets markup class attribute to either 'odd' or
                // 'even' for decoration
                return new OddEvenItem<>(id, index, model);
            }
        };

        // because we are in a form we need to preserve state of the component
        // hierarchy (because it might contain things like form errors that
        // would be lost if the hierarchy for each item was recreated every
        // request by default), so we use an item reuse strategy.
        refreshingView.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());


        form.add(refreshingView);
    }

    /**
     * Panel that houses row-actions
     */
    private class ActionPanel extends Panel
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
                    setSelected((User)ActionPanel.this.getDefaultModelObject());
                }
            });

            SubmitLink removeLink = new SubmitLink("remove", form)
            {
                @Override
                public void onSubmit()
                {
                    User user = (User)ActionPanel.this.getDefaultModelObject();
                    info("Removed contact " + user);
                    UserHelper.listOfUsers().remove(user);
                }
            };
            removeLink.setDefaultFormProcessing(false);
            add(removeLink);
        }
    }
}
