package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    private TummyCircleService tummyCircleService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private TummyUserService tummyUserService;

    @Override
    public List<Recipes> getHomeContents(String username, Integer page) {
        List<Friend> friends = tummyCircleService.findAllFriends(username);
        return recipeService.findFriendsRecipes(friends, page);
    }

    @Override
    public List<Recipes> getRecipesByIngridents(List<String> ingridents, Integer page, String cuisine, Long cookingTime) {
        return recipeService.findRecipeByIngridents(ingridents, page, cuisine, cookingTime);
    }

}
