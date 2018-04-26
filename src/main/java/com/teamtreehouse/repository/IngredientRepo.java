package com.teamtreehouse.repository;

import com.teamtreehouse.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends CrudRepository<Ingredient, Long>
{
}
