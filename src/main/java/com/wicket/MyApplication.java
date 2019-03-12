package com.wicket;

import com.wicket.login.LoginPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import com.wicket.hello.Hello;

public class MyApplication extends WebApplication
{

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		//return Hello.class;
		return LoginPage.class;
	}

	@Override
	public void init()
	{
		super.init();
		// add your configuration here
	}

}
