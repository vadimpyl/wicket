package com.wicket.hello;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class Hello extends WebPage {

	private static final long serialVersionUID = 1L;

    public Hello() {

        add(new Label("message", "Hello World, Wicket"));

    }
}
