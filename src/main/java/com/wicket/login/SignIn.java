package com.wicket.login;

import com.wicket.helper.SignInSession;
import com.wicket.register.Register;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class SignIn extends WebPage
{

    Button register = new Button("register") {
        public void onSubmit() {
            getRequestCycle().setResponsePage(Register.class);

            info("button1.onSubmit executed");
        }
    };

    public SignIn()
    {
        // Create feedback panel and add to panelage
        add(new FeedbackPanel("feedback"));

        // Add sign-in form to page
        add(new SignInForm("signInForm"));
    }

    /**
     * Sign in form
     */
    public final class SignInForm extends Form<Void>
    {
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        // El-cheapo model for form
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
            // Attach textfield components that edit properties map model
            add(new TextField<>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<>(properties, PASSWORD)));
            add(new BookmarkablePageLink<>("register", Register.class));
        }

        @Override
        public final void onSubmit()
        {
            // Get session info
            SignInSession session = getMySession();

            // Sign the user in
            if (session.signIn(getUsername(), getPassword()))
            {
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            }
            else
            {
                // Get the error message from the properties file associated with the Component
                String errmsg = getString("loginError", null, "Unable to sign you in");

                // Register the error message with the feedback panel
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
