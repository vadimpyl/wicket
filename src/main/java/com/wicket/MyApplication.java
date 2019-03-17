package com.wicket;

import com.wicket.application.Welcome;
import com.wicket.helper.AuthenticatedWebPage;
import com.wicket.helper.SignInSession;
import com.wicket.login.SignIn;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;

public class MyApplication extends WebApplication
{

	@Override
	public Class<? extends Page> getHomePage()
	{
		return Welcome.class;
	}

	@Override
	public Session newSession(Request request, Response response)
	{
		return new SignInSession(request);
	}

	@Override
	protected void init()
	{
		super.init();

		// Register the authorization strategy
		getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy()
		{
			@Override
			public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
					Class<T> componentClass)
			{
				// Check if the new Page requires authentication (implements the marker interface)
				if (AuthenticatedWebPage.class.isAssignableFrom(componentClass))
				{
					// Is user signed in?
					if (((SignInSession)Session.get()).isSignedIn())
					{
						// okay to proceed
						return true;
					}

					// Intercept the request, but remember the target for later.
					// Invoke Component.continueToOriginalDestination() after successful logon to
					// continue with the target remembered.

					throw new RestartResponseAtInterceptPageException(SignIn.class);
				}

				// okay to proceed
				return true;
			}
		});
	}

}
