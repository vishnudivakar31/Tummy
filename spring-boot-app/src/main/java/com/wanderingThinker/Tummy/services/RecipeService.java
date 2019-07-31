package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.supportingdocuments.Rating;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;

import java.util.List;

public interface RecipeService {
    void postRecipe(Recipes recipes);
    void deleteRecipe(String id);
    void deleteAllRecipe(String username);
    void updateRecipe(Recipes recipes);
    List<Recipes> findAllRecipes(String username);
    Recipes findRecipeById(String username, String id) throws TummyException;
    Recipes likeRecipe(String username, String id) throws TummyException;
    Recipes postRating(String id, Rating rating) throws TummyException;
    Recipes deleteRating(String username, String id) throws TummyException;
    Recipes reportAbuse(String username, String id) throws TummyException;
}
