package com.wicket.application;

import com.wicket.helper.ActionPanel;
import com.wicket.helper.AuthenticatedWebPage;
import com.wicket.helper.DateFilter;
import com.wicket.helper.SortableUserDataProvider;
import com.wicket.model.User;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import java.util.ArrayList;
import java.util.List;

public class FilterTable extends WebPage implements AuthenticatedWebPage
{
    public FilterTable()
    {
        //wyswietlamy welcome user
        add(new Label("userName", new PropertyModel<>(this, "session.user")));

        List<IColumn<User, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<>(new ResourceModel("login"), "login"));
        columns.add(new PropertyColumn<>(new ResourceModel("firstName"), "firstName"));
        columns.add(new PropertyColumn<>(new ResourceModel("lastName"), "lastName"));
        columns.add(new PropertyColumn<>(new ResourceModel("birthCity"), "birthCity"));
        columns.add(new PropertyColumn<>(new ResourceModel("birthCountry"), "birthCountry"));
        columns.add(new AbstractColumn<User, String>(new ResourceModel("delete"))
        {
            @Override
            public void populateItem(Item<ICellPopulator<User>> cellItem, String componentId,
                                     IModel<User> model)
            {
                cellItem.add(new ActionPanel(componentId, model));
            }
        });

        SortableUserDataProvider dataProvider = new SortableUserDataProvider();
        columns = new ArrayList<>(columns);
        columns.add(new PropertyColumn<>(new ResourceModel("birthDate"), "birthDate"));

        DataTable<User, String> dataTable = new DefaultDataTable<>("table", columns, dataProvider, 10);
        dataTable.setOutputMarkupId(true);

        //AjaxFallbackDefaultDataTable <User, String> dataTable = new AjaxFallbackDefaultDataTable<>("table", columns, dataProvider, 15);

        // add(defaultDataTable);


        FilterForm<DateFilter> filterForm = new FilterForm<>("filterForm", dataProvider);

        filterForm.add(new TextField<>("dateFrom", PropertyModel.of(dataProvider, "filterState.dateFrom")));
        filterForm.add(new TextField<>("dateTo", PropertyModel.of(dataProvider, "filterState.dateTo")));

        add(filterForm);
        FilterToolbar filterToolbar = new FilterToolbar(dataTable, filterForm);
        //dataTable.addTopToolbar(filterToolbar);
        //dataTable.addTopToolbar(new NavigationToolbar(dataTable));
        //dataTable.addTopToolbar(new HeadersToolbar<>(dataTable, dataProvider));
        filterForm.add(dataTable);
    }
}
