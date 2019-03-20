package com.wicket.helper;

import com.wicket.model.User;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SortableUserDataProvider extends SortableDataProvider<User, String> implements IFilterStateLocator<DateFilter>
{


    DateFilter dateFilter = new DateFilter();
    public SortableUserDataProvider()
    {
        // set default sort
        setSort("login", SortOrder.DESCENDING);
    }

    private List<User> filterContacts(List<User> contactsFound)
    {
        ArrayList<User> result = new ArrayList<>();
        Date dateFrom = dateFilter.getDateFrom();
        Date dateTo = dateFilter.getDateTo();

        for (User user : contactsFound)
        {
            Date bornDate = user.getBirthDateDate();

            if(dateFrom != null && bornDate.before(dateFrom))
            {
                continue;
            }

            if(dateTo != null && bornDate.after(dateTo))
            {
                continue;
            }

            result.add(user);
        }

        return result;
    }

    @Override
    public Iterator<? extends User> iterator(long first, long count)
    {
        List<User> list = UserHelper.listOfUsers();
        //return list.iterator();
        return filterContacts(list).
                subList((int)first, (int)(first + count)).
                iterator();
    }

    @Override
    public long size()
    {
        return filterContacts(UserHelper.listOfUsers()).size();
    }

    @Override
    public IModel<User> model(User object)
    {
        return new DetachableUserModel(object);
        //return Model.of(object);
    }

    public DateFilter getFilterState()
    {
        return dateFilter;
    }

    public void setFilterState(DateFilter state)
    {
        dateFilter  = state;
    }
}
