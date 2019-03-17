package com.wicket.application;

import com.wicket.helper.AuthenticatedWebPage;
import com.wicket.helper.SortableUserDataProvider;
import com.wicket.model.User;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends WebPage implements AuthenticatedWebPage
{

    public Welcome()
    {
        //wyswietlamy welcome user
        add(new Label("userName", new PropertyModel<>(this, "session.user")));

        List<IColumn<User, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<>(new ResourceModel("login"), "login"));

        SortableUserDataProvider dataProvider = new SortableUserDataProvider();
        DataTable<User, String> dataTable = new DefaultDataTable<>("table", columns,
                dataProvider, 8);
        dataTable.addTopToolbar(new HeadersToolbar<>(dataTable, dataProvider));

        add(dataTable);

    }
}