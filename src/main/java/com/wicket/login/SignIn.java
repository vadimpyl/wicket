package com.wicket.login;

import com.wicket.helper.SignInSession;
import com.wicket.register.Register;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class SignIn extends WebPage
{

    public SignIn()
    {
        add(new SignInForm("signInForm"));
    }

    /**
     * Sign in form
     */
    public final class SignInForm extends Form<Void>
    {
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";
        private final ValueMap properties = new ValueMap();


        /**
         * Constructor
         *
         * @param id
         *            id of the form component
         */
        public SignInForm(final String id)
        {
            super(id);
            add(new TextField<>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<>(properties, PASSWORD)));
            add(new BookmarkablePageLink<>("register", Register.class));
            add(new FeedbackPanel("feedback"));
        }
        @Override
        public final void onSubmit()
        {
            SignInSession session = getMySession();

            if (session.signIn(getUsername(), getPassword()))
            {
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            }
            else
            {
                String errmsg = getString("loginError", null, "Unable to sign you in");

                error(errmsg);
            }
        }

        /**
         * @return
         */
        private String getPassword()
        {
            return properties.getString(PASSWORD);
        }

        /**
         * @return
         */
        private String getUsername()
        {
            return properties.getString(USERNAME);
        }

        /**
         * @return
         */
        private SignInSession getMySession()
        {
            return (SignInSession)getSession();
        }
    }
}
