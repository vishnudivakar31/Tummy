package com.wanderingThinker.Tummy.repositories;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.supportingdocuments.Ingrident;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipesRepository extends PagingAndSortingRepository<Recipes, String> {
    List<Recipes> findByUsername(String username, Pageable pageable);
    List<Recipes> findByTitle(String title);
    List<Recipes> findByCuisine(String cuisine, Pageable pageable);

    @Query("{'cookingTime' : {$lte : ?0}}")
    List<Recipes> findByCookingTime(Long cookingTime);

    void deleteByUsername(String username);

    List<Recipes> findByUsernameIn(List<String> usernames, Pageable pageable);
    List<Recipes> findByIngridentsIn(List<Ingrident> ingridents, Pageable pageable);
}
