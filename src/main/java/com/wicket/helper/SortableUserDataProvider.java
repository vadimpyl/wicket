package com.wicket.helper;

import com.wicket.model.User;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class SortableUserDataProvider extends SortableDataProvider<User, String>
{

    @Override
    public Iterator<? extends User> iterator(long first, long count)
    {
        List<User> list = UserHelper.listOfUsers();
        return list.iterator();
    }

    @Override
    public long size()
    {
        return UserHelper.listOfUsers().size();
    }

    @Override
    public IModel<User> model(User object)
    {
        return new DetachableUserModel(object);
        //return Model.of(object);
    }
}
