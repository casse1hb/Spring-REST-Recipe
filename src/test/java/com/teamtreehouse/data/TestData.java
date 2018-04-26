package com.teamtreehouse.data;

import com.teamtreehouse.model.*;
import java.util.Arrays;
import java.util.List;

public class TestData
{

    public static List<Recipe> recipeList()
    {
        return Arrays.asList(recipe1(), recipe2());
    }

    public static Recipe recipe1()
    {
        return new Recipe.RecipeBuilder("Joes Eggs with a Sunny Side Up :)", Category.BREAKFAST)
                .withDescription("Joes famous eggs over rice recipe he gave me one time.")
                .withPrepTime(5)
                .withCookTime(20)
                .withDirections(directions1())
                .withIngredients(ingredients1())
                .withUser(user1())
                .build();
    }

    public static Recipe recipe2()
    {
        return new Recipe.RecipeBuilder("Soft Shell Tacos by Alanna <3", Category.LUNCH)
                .withDescription("The tacos that Alanna makes me when I'm sad from my code not working")
                .withPrepTime(5)
                .withCookTime(10)
                .withDirections(directions2())
                .withIngredients(ingredients2())
                .withUser(user1())
                .build();
    }

    public static List<Direction> directions1()
    {
        return Arrays.asList(
                new Direction("-Bring water to a boil in a pot, add dry rice, stir for 10 minutes"),
                new Direction("-Drain water from rice and add sugar, mix thoroughly"),
                new Direction("-Heat and add butter to a clean skillet."),
                new Direction("-Crack and add eggs onto a skillet, no shells unless you're into that."),
                new Direction("-Cook eggs until bottoms are golden brown and tops are slightly solid with a gooey center yolk."),
                new Direction("-Place eggs on top of rice according to your preferred portion size."),
                new Direction( "-Add salt, pepper, and diced tomatoes, to your discretion."),
                new Direction("-Enjoy the masterpiece you just created.")
        );
    }

    public static List<Direction> directions2()
    {
        return Arrays.asList(
                new Direction("-Drain black bean syrup from can into sink."),
                new Direction("-Empty contents of black beans into pan, heat lightly"),
                new Direction("-Once heated properly empty pan into bowl"),
                new Direction("-Take flour tortilla place 1 spoonful of black beans"),
                new Direction("-Wash Lettuce and peel top layer away"),
                new Direction("-Rip fresh lettuce according to your preference, add to tortilla with black beans"),
                new Direction("-Open Diced tomato can and drain liquid."),
                new Direction("-add a spoonful of diced tomato to tortilla"),
                new Direction("-Place a scoop of queso ontop of tortilla contents"),
                new Direction( "-Roll Tortilla with all contents inside."),
                new Direction( "-Enjoy the soft taco you just created, then make about 20 more cause they're awesome!")
        );
    }

    public static List<Ingredient> ingredients1()
    {
        return Arrays.asList(
                new Ingredient("Eggs", "Fresh", "2"),
                new Ingredient("Rice", "N/A", "~1 Cup"),
                new Ingredient("Tomato", "Fresh", "1/4 Cup "),
                new Ingredient( "Sugar", "N/A", "1 Tea Spoon"),
                new Ingredient( "Salt", "N/A", "A pinch"),
                new Ingredient( "Pepper", "N/A", "A pinch")
        );
    }

    public static List<Ingredient> ingredients2()
    {
        return Arrays.asList(
                new Ingredient("Black Beans", "Canned", "1 Can"),
                new Ingredient("Diced Tomato", "Canned", "1 Can"),
                new Ingredient("Flour Tortilla", "Packaged", "1 Pack of 10"),
                new Ingredient("Lettuce", "Fresh", "1 head"),
                new Ingredient("Preferred Queso", "Packaged", "1 Jar"),
                new Ingredient("Can Opener", "Whatever is in your drawer", "1")
        );
    }

    public static User user1()
    {
        User user = new User("Harry");
        user.setPassword("password");
        return user;
    }

    public static User user2()
    {
        User user = new User("Joe");
        user.setPassword("password");
        return user;
    }

    public static User admin()
    {
        User user = new User("Admin");
        user.setPassword("password");
        user.setRole(User.Role.ADMIN);
        return user;
    }
}
