package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.documents.TummyUser;

import java.util.List;

public interface HomeService {
    List<Recipes> getHomeContents(String name, Integer page);

    List<Recipes> getRecipesByIngridents(List<String> ingridents, Integer page, String cuisine, Long cookingTime);

}
