package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.supportingdocuments.*;

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
    Recipes postComment(String id, Comments comments) throws TummyException;
    Recipes clearAbuse(String username, String id) throws TummyException;
    List<Recipes> findFriendsRecipes(List<Friend> friends, Integer page);

    List<Recipes> findRecipeByIngridents(List<String> ingridents, Integer page, String cuisine, Long cookingTime);
}
