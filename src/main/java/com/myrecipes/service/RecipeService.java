package com.myrecipes.service;

import com.myrecipes.exception.RecipeNotFoundException;
import com.myrecipes.model.Recipe;
import com.myrecipes.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipeService
{
    List<Recipe> findAll();

    List<Category> allCategories();

    List<Recipe> findByCategory(Category category);

    List<Recipe> findByCategory(String category);

    List<Recipe> findByDescription(String search);

    List<Recipe> findByIngredient(String search);

    Recipe findById(Long id) throws RecipeNotFoundException;

    void save(Recipe recipe, MultipartFile file);

    void delete(Long id) throws RecipeNotFoundException;
}
