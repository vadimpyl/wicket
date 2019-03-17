package com.wicket.register;

import com.wicket.helper.SignInSession;
import com.wicket.helper.UserHelper;
import com.wicket.model.User;
import javafx.scene.control.DatePicker;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends WebPage
{
    public Register()
    {
        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        add(new Label("message", "ReGISTER"));
        add(new RegisterForm("registerForm"));
    }

    /**
     * Sign in form
     */
    public final class RegisterForm extends Form<Void>
    {
        private final ValueMap properties = new ValueMap();
        final LocalDateTextField dateField = new LocalDateTextField("birthday", new PropertyModel<>(properties, "birthday"),
                "dd-MM-yyyy");

        /**
         * Constructor
         *
         * @param id
         *            id of the form component
         */
        public RegisterForm(final String id)
        {
            super(id);
            add(new TextField<>("login", new PropertyModel<String>(properties, "login"))
                    .setRequired(true));
            add(new TextField<>("firstName", new PropertyModel<String>(properties, "firstName"))
                    .setRequired(true));
            add(new TextField<>("lastName", new PropertyModel<String>(properties, "firstName")));
            //add(new DateTextField("birthday", new PropertyModel<Date>(properties, "birthday"), "dd-MM-yyyy")
             //       .add(new DatePicker()));
            add(dateField);
            add(new TextField<>("postalCode", new PropertyModel<String>(properties, "postalCode")));
            add(new TextField<>("birthCity", new PropertyModel<String>(properties, "birthCity")));
            add(new TextField<>("birthCountry", new PropertyModel<String>(properties, "birthCountry")));

            add(new PasswordTextField("password", new PropertyModel<>(properties, "password"))
                    .setRequired(true));
            add(new PasswordTextField("confirmPassword", new PropertyModel<>(properties, "confirmPassword"))
                    .setRequired(true));
            getDefaultModelObject();

        }

        @Override
        public final void onSubmit()
        {
            if(getPassword().equals(getConfirmPassword()))
            {
                UserHelper.addToList(new User(10, getLogin(), getFirstName(), getLastName(), getBirthday(), getBirthCity(), getBirthCountry(), getPassword()));
                SignInSession session = getMySession();
                session.signIn(getLogin(), getPassword());
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            }
            else
            {
                error("Passwords are not the same.");
            }
        }

        private String getLogin()
        {
            return properties.getString("login");
        }

        private String getFirstName()
        {
            return properties.getString("firstName");
        }
        private String getLastName()
        {
            return properties.getString("lastName");
        }
        private Date getBirthday()
        {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-mm-dd").parse(properties.getString("birthday"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        private String getPostalCode()
        {
            return properties.getString("postalCode");
        }
        private String getBirthCity()
        {
            return properties.getString("birthCity");
        }
        private String getBirthCountry()
        {
            return properties.getString("birthCountry");
        }

        private String getPassword()
        {
            return properties.getString("password");
        }

        private String getConfirmPassword()
        {
            return properties.getString("confirmPassword");
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
