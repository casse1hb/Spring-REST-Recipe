package com.myrecipes.core;

import com.myrecipes.model.*;
import com.myrecipes.repository.RecipeRepository;
import com.myrecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner
{
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataLoader(RecipeRepository recipeRepository, UserRepository userRepository)
    {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    //Default Users/passwords, Ingredients, and Recipes. Available to all.
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        User harry = new User("User1");
        harry.setPassword("password");
        userRepository.save(harry);

        User joe = new User("DearFriendJoe");
        joe.setPassword("believe_in_yourself_Harry");
        userRepository.save(joe);

        User craig = new User("CSD");
        craig.setPassword("I_Like_Tacos");
        craig.setRole(User.Role.ADMIN);
        userRepository.save(craig);

        User chris = new User("CR");
        chris.setPassword("I_Teach_Kids_To_Code");
        chris.setRole(User.Role.ADMIN);
        userRepository.save(craig);

        List<Ingredient> ingredients1 = new ArrayList<>(Arrays.asList(
                new Ingredient("Eggs", "Fresh", "2"),
                new Ingredient("Rice", "N/A", "~1 Cup"),
                new Ingredient("Tomato", "Fresh", "1/4 Cup "),
                new Ingredient( "Sugar", "N/A", "1 Tea Spoon"),
                new Ingredient( "Salt", "N/A", "A pinch"),
                new Ingredient( "Pepper", "N/A", "A pinch")
        ));

        List<Ingredient> ingredients2 = new ArrayList<>(Arrays.asList(
                new Ingredient("Black Beans", "Canned", "1 Can"),
                new Ingredient("Diced Tomato", "Canned", "1 Can"),
                new Ingredient("Flour Tortilla", "Packaged", "1 Pack of 10"),
                new Ingredient("Lettuce", "Fresh", "1 head"),
                new Ingredient("Preferred Queso", "Packaged", "1 Jar"),
                new Ingredient("Can Opener", "Whatever is in your drawer", "1")
        ));

        List<Direction> directions1 = new ArrayList<>(Arrays.asList(
                new Direction("-Bring water to a boil in a pot, add dry rice, stir for 10 minutes"),
                new Direction("-Drain water from rice and add sugar, mix thoroughly"),
                new Direction("-Heat and add butter to a clean skillet."),
                new Direction("-Crack and add eggs onto a skillet, no shells unless you're into that."),
                new Direction("-Cook eggs until bottoms are golden brown and tops are slightly solid with a gooey center yolk."),
                new Direction("-Place eggs on top of rice according to your preferred portion size."),
                new Direction( "-Add salt, pepper, and diced tomatoes, to your discretion."),
                new Direction("-Enjoy the masterpiece you just created.")
        ));

        List<Direction> directions2 = new ArrayList<>(Arrays.asList(
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
        ));

        List<Recipe> recipes = Arrays.asList(
                new Recipe.RecipeBuilder("Joes Eggs with a Sunny Side Up :)", Category.BREAKFAST)
                        .withDescription("Joes famous eggs over rice recipe he gave me one time.")
                        .withPrepTime(5)
                        .withCookTime(20)
                        .withIngredients(ingredients1)
                        .withDirections(directions1)
                        .build(),

                new Recipe.RecipeBuilder("Soft Shell Tacos by Alanna <3", Category.DINNER)
                        .withDescription("The tacos that Alanna makes me when I'm sad from my code not working")
                        .withPrepTime(5)
                        .withCookTime(10)
                        .withIngredients(ingredients2)
                        .withDirections(directions2)
                        .build()
        );

        harry.toggleFavorite(recipes.get(0));

        recipes.forEach(recipeRepository::save);
        userRepository.save(harry);
    }
}
