package com.teamtreehouse.model;

public enum Category
{
    ALL("All Categories"),
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack");

    private String name;

    Category(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
