package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("")
    List<Recipes> findAllRecipe(Principal principal) {
        List<Recipes> recipes = new ArrayList<>();
        recipes = recipeService.findAllRecipes(principal.getName());
        return recipes;
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> findRecipe(Principal principal, @PathVariable("id") String id) {
        Recipes recipes =recipeService.findRecipeById(principal.getName(), id);
        if(recipes != null) {
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }
        Map<String, String> msg = new HashMap<>();
        msg.put("msg", "recipe not found");
        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping()
    ResponseEntity<Map<String, String>> postRecipe(Principal principal, @RequestBody Recipes recipes) {
        Map<String, String> msg = new HashMap<>();
        recipes.setUsername(principal.getName());
        try {
            recipeService.postRecipe(recipes);
            msg.put("msg", "recipe saved");
            return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    ResponseEntity<Map<String, String>> updateRecipe(@RequestBody Recipes recipes) {
        Map<String, String> msg = new HashMap<>();
        try {
            recipeService.updateRecipe(recipes);
            msg.put("msg", "recipe updated");
            return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    ResponseEntity<Map<String, String>> deleteRecipe(Principal principal) {
        Map<String, String> msg = new HashMap<>();
        try {
            recipeService.deleteAllRecipe(principal.getName());
            msg.put("msg", "recipe updated");
            return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteRecipeById(Principal principal, @PathVariable("id") String id) {
        Map<String, String> msg = new HashMap<>();
        try {
            recipeService.deleteRecipe(id);
            msg.put("msg", "recipe updated");
            return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
