package com.teamtreehouse.core;

import com.teamtreehouse.model.*;
import com.teamtreehouse.repository.RecipeRepo;
import com.teamtreehouse.repository.UserRepo;
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
    private final RecipeRepo recipeRepo;
    private final UserRepo userRepo;

    @Autowired
    public DataLoader(RecipeRepo recipeRepo, UserRepo userRepo)
    {
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
    }

    //Default Users/passwords, Ingredients, and Recipes. Available to all.
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        User harry = new User("User1");
        harry.setPassword("password");
        userRepo.save(harry);

        User joe = new User("DearFriendJoe");
        joe.setPassword("believe_in_yourself_Harry");
        userRepo.save(joe);

        User craig = new User("CSD");
        craig.setPassword("I_Like_Tacos");
        craig.setRole(User.Role.ADMIN);
        userRepo.save(craig);

        User chris = new User("CR");
        chris.setPassword("I_Teach_Kids_To_Code");
        chris.setRole(User.Role.ADMIN);
        userRepo.save(craig);

        //Ingredient for recipe 1
        List<Ingredient> ingredients1 = new ArrayList<>(Arrays.asList(
                new Ingredient("Eggs", "Fresh", "2"),
                new Ingredient("Rice", "N/A", "~1 Cup"),
                new Ingredient("Tomato", "Fresh", "1/4 Cup "),
                new Ingredient( "Sugar", "N/A", "1 Tea Spoon"),
                new Ingredient( "Salt", "N/A", "A pinch"),
                new Ingredient( "Pepper", "N/A", "A pinch")
        ));
        //ingredient for recipe 2
        List<Ingredient> ingredients2 = new ArrayList<>(Arrays.asList(
                new Ingredient("Black Beans", "Canned", "1 Can"),
                new Ingredient("Diced Tomato", "Canned", "1 Can"),
                new Ingredient("Flour Tortilla", "Packaged", "1 Pack of 10"),
                new Ingredient("Lettuce", "Fresh", "1 head"),
                new Ingredient("Preferred Queso", "Packaged", "1 Jar"),
                new Ingredient("Can Opener", "Whatever is in your drawer", "1")
        ));

        //recipe 3 ingredients
        List<Ingredient> ingredients3 = new ArrayList<>(Arrays.asList(
                new Ingredient("Zucchini", "Fresh", "1"),
                new Ingredient("Sliced PepperJack Cheese", "Packaged or Fresh", "1 Package or Container"),
                new Ingredient("Hunger", "Constant", "Infinity")
        ));

        //recipe 4 ingredients
        List<Ingredient> ingredients4 = new ArrayList<>(Arrays.asList(
                new Ingredient("Ground Beef", "Fresh", "1/4 lb"),
                new Ingredient("Sliced Cheddar Cheese", "Packaged or Fresh", "1 Package or Container"),
                new Ingredient("Ketchup","Packaged", "1 tablespoon"),
                new Ingredient("Mustard","Packaged", "1 tablespoon"),
                new Ingredient("Pickles","Sliced type", "3 slices"),
                new Ingredient("Onions","Fresh", "1 tablespoon diced"),
                new Ingredient("Hunger", "Constant", "Infinity")
        ));

        //direction for recipe 1
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

        //direction for recipe 2
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

        List<Direction> directions3 = new ArrayList<>(Arrays.asList(
                new Direction("-Wash zucchini and chop into 2cm thick circular pieces ."),
                new Direction("-Grease pan with butter OR canola oil"),
                new Direction("-Place zucchini pieces onto pan putting slices of pepper jack cheese on top, making sure to leave some cheese falling off because then it becomes golden brown and crispy.....mmmmm"),
                new Direction( "-Cover and cook on medium for ~ 5-10 min, once cooled, enjoy. You can also sprinkle some parmesan on top, but that's just me")
        ));

        List<Direction> directions4 = new ArrayList<>(Arrays.asList(
                new Direction("-Turn on Pulp Fiction."),
                new Direction("-Take ground beef and ball into circles the size of your palm and half as thick as your palm"),
                new Direction("-Place beef onto grill, pan, or George Foreman grill"),
                new Direction( "-Cover and cook on medium for ~ 10-15 min, check center for preferred cook. Recommended: medium rare"),
                new Direction("-Once cooked appropriately plate onto a sesame seed burger bun and place one slice of cheese. Recommended: Cheddar"),
                new Direction("-Add ketchup, onions, mustard, pickles, and lettuce on top of burger"),
                new Direction("-Place top burger bun onto patty and serve.")
                ));

//recipe 1
        List<Recipe> recipes = Arrays.asList(
                new Recipe.RecipeBuilder("Joes Eggs with a Sunny Side Up :)", Category.BREAKFAST)
                        .withDescription("Joes famous eggs over rice recipe he gave me one time.")
                        .withPrepTime(5)
                        .withCookTime(20)
                        .withIngredients(ingredients1)
                        .withDirections(directions1)
                        .build(),
//recipe 2
                new Recipe.RecipeBuilder("Soft Shell Tacos by Alanna <3", Category.DINNER)
                        .withDescription("The tacos that Alanna makes me when I'm sad from my code not working")
                        .withPrepTime(5)
                        .withCookTime(10)
                        .withIngredients(ingredients2)
                        .withDirections(directions2)
                        .build(),

//recipe 3
                new Recipe.RecipeBuilder("Cheesy Zucchini", Category.SNACK)
                        .withDescription("Fried Zucchini with Cheese")
                        .withPrepTime(5)
                        .withCookTime(10)
                        .withIngredients(ingredients3)
                        .withDirections(directions3)
                        .build(),

                new Recipe.RecipeBuilder("Royal With Cheese", Category.LUNCH)
                        .withDescription("A Tasty Burger. For 'Detroit Style' Refer to future posts. " +
                                        " This recipe will include: Smoked Brisket, Southwest Corn Relish," +
                                        " Caramelized Onions, Avocado, Smoked Gouda Cheese Fondue, Creamy Cole Slaw, and Royale Sauce. ")
                        .withPrepTime(5)
                        .withCookTime(10)
                        .withIngredients(ingredients4)
                        .withDirections(directions4)
                        .build()
        );

        harry.toggleFavorite(recipes.get(0));

        recipes.forEach(recipeRepo::save);
        userRepo.save(harry);
    }
}
