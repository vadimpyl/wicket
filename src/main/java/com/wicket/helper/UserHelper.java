package com.wicket.helper;

import com.wicket.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserHelper
{
    static List<User> userList = new ArrayList<User>() {{
        add(new User("user1","John","Bee",parseDate("1994-02-01"),"London","England","a"));
        add(new User("user2","Krzysztof","Wilski", parseDate("1987-01-23"),"Szczecin","Poland","a"));
        add(new User("user3","Andrew","Hook", parseDate("1961-11-29"),"Berlin","Germany", "a"));
        add(new User("user4","Anna","Clark", parseDate("1999-08-09"),"Paris","France", "a"));
        add(new User("user5","Dee","Knock", parseDate("1974-07-14"),"Madrid","Spain", "a"));
        add(new User("user6","Foo","Lant", parseDate("2001-12-31"),"Lisbon","Portugal", "a"));
        add(new User("user7","Ke","Larson",parseDate("1959-05-16"),"Prague","Czech Republic", "a"));
        add(new User("user8","Richard","Ant", parseDate("1981-03-04"),"Rome","Italy", "a"));
        add(new User("user9","Angelina","Goulton", parseDate("1990-09-29"),"Vienna","Austria", "a"));
        add(new User("user10","Stephan","Took", parseDate("1989-01-01"),"Oslo","Norway", "a"));
    }};
    public static List<User> listOfUsers()
    {
        return userList;
    }

    public static void addToList(final User user)
    {
        userList.add(user);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}