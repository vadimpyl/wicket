package com.wicket.helper;

import com.wicket.model.User;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableUserModel extends LoadableDetachableModel<User>
{
    private final long id;


    public DetachableUserModel(User c)
    {
        this(c.getId());
    }

    public DetachableUserModel(long id)
    {
        if (id == 0)
        {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    @Override
    protected User load()
    {
        return UserHelper.load(id);
    }
}
