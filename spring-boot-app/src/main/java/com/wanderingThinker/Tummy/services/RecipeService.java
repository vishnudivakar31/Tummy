package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;

import java.util.List;

public interface RecipeService {
    void postRecipe(Recipes recipes);
    void deleteRecipe(String id);
    void deleteAllRecipe(String username);
    void updateRecipe(Recipes recipes);
    List<Recipes> findAllRecipes(String username);
    Recipes findRecipeById(String username, String id);
}
