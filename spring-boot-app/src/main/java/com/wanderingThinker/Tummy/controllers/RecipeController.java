package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.services.RecipeService;
import com.wanderingThinker.Tummy.supportingdocuments.Comments;
import com.wanderingThinker.Tummy.supportingdocuments.Rating;
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
    public List<Recipes> findAllRecipe(Principal principal) {
        List<Recipes> recipes = new ArrayList<>();
        recipes = recipeService.findAllRecipes(principal.getName());
        return recipes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findRecipe(Principal principal, @PathVariable("id") String id) {
        try {
            Recipes recipes =recipeService.findRecipeById(principal.getName(), id);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> postRecipe(Principal principal, @RequestBody Recipes recipes) {
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
    public ResponseEntity<Map<String, String>> updateRecipe(@RequestBody Recipes recipes) {
        Map<String, String> msg = new HashMap<>();
        try {
            recipeService.updateRecipe(recipes);
            msg.put("msg", "recipe updated");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, String>> deleteRecipe(Principal principal) {
        Map<String, String> msg = new HashMap<>();
        try {
            recipeService.deleteAllRecipe(principal.getName());
            msg.put("msg", "recipe deleted");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRecipeById(Principal principal, @PathVariable("id") String id) {
        Map<String, String> msg = new HashMap<>();
        try {
            recipeService.deleteRecipe(id);
            msg.put("msg", "recipe deleted");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<Object> likeRecipe(Principal principal, @PathVariable("id") String id) {
        Map<String, String> msg = new HashMap<>();
        try {
            Recipes recipes = recipeService.likeRecipe(principal.getName(), id);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch(Exception e) {
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ratings/{id}")
    public ResponseEntity<Object> postRatings(Principal principal, @PathVariable("id") String id, @RequestBody Rating rating) {
        rating.setUsername(principal.getName());
        try {
            Recipes recipes = recipeService.postRating(id, rating);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch(Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Object> deleteRating(Principal principal, @PathVariable("id") String id) {
        try {
            Recipes recipes = recipeService.deleteRating(principal.getName(), id);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch(Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/abusive/{id}")
    public ResponseEntity<Object> abusiveRecipe(Principal principal, @PathVariable("id") String id) {
        try {
            Recipes recipes = recipeService.reportAbuse(principal.getName(), id);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch(Exception e) {
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", e.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
