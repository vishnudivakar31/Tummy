package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    private void calculateRatings(Recipes recipes) {
        if(recipes.getRatings() != null && recipes.getRatings().size() > 0) {
            Float ratings = recipes.getRatings().stream().map(i -> i.getRating()).reduce(0f, Float::sum);
            recipes.setAvgRatings(ratings / recipes.getRatings().size());
        }
    }

    public void postRecipe(Recipes recipes) {
        calculateRatings(recipes);
        recipes.setPosted_date(new Date(System.currentTimeMillis()));
        recipes.setUpdated_date(new Date(System.currentTimeMillis()));
        recipesRepository.save(recipes);
    }

    public void deleteRecipe(String id) {
        recipesRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecipe(String username) {
        recipesRepository.deleteByUsername(username);
    }

    public void updateRecipe(Recipes recipes) {
        calculateRatings(recipes);
        recipes.setUpdated_date(new Date(System.currentTimeMillis()));
        recipesRepository.save(recipes);
    }

    @Override
    public List<Recipes> findAllRecipes(String username) {
        return recipesRepository.findByUsername(username,
                PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "posted_date")));
    }

    @Override
    public Recipes findRecipeById(String username, String id) {
        Optional<Recipes> recipes = recipesRepository.findById(id);
        if(recipes.isPresent()) {
            return recipes.get().getUsername().equals(username) ? recipes.get() : null;
        }
        return null;
    }
}
