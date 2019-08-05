package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;

import java.util.List;

public interface HomeService {
    List<Recipes> getHomeContents(String name, Integer page);
}
