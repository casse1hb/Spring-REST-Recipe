package com.myrecipes.repository;

import com.myrecipes.model.Category;
import com.myrecipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>
{
    List<Recipe> findAll();

    List<Recipe> findByCategory(Category category);

    List<Recipe> findByDescriptionContainingIgnoreCase(String search);

    List<Recipe> findByIngredientsItemIgnoreCase(String search);

    Recipe findOne(Long id);
}
