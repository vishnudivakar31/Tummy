package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.repositories.RecipesRepository;
import com.wanderingThinker.Tummy.repositories.TummyUserRepository;
import com.wanderingThinker.Tummy.supportingdocuments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private TummyUserRepository tummyUserRepository;

    private void calculateRatings(Recipes recipes) {
        if(recipes.getRatings() != null && recipes.getRatings().size() > 0) {
            Float ratings = recipes.getRatings().stream().map(i -> i.getRating()).reduce(0f, Float::sum);
            recipes.setAvgRatings(ratings / recipes.getRatings().size());
        }
    }

    private void findIsAbusive(Recipes recipes) {
        Boolean recipeStatus = recipes.getAbusiveReportList() != null && recipes.getAbusiveReportList().size() > 0;
        recipes.setAbusive(recipeStatus);
    }

    public void postRecipe(Recipes recipes) {
        calculateRatings(recipes);
        findIsAbusive(recipes);
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
        findIsAbusive(recipes);
        recipes.setUpdated_date(new Date(System.currentTimeMillis()));
        recipesRepository.save(recipes);
    }

    @Override
    public List<Recipes> findAllRecipes(String username) {
        return recipesRepository.findByUsername(username,
                PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "posted_date")));
    }

    @Override
    public Recipes findRecipeById(String username, String id) throws TummyException {
        Optional<Recipes> recipes = recipesRepository.findById(id);
        if(recipes.isPresent()) {
            return recipes.get().getUsername().equals(username) ? recipes.get() : null;
        }
        throw new TummyException("recipe not found.");
    }

    @Override
    public Recipes likeRecipe(String username, String id) throws TummyException {
        Optional<Recipes> optionalRecipes = recipesRepository.findById(id);
        if(optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            List<String> likeList = recipes.getLikesList() != null ? recipes.getLikesList() : new ArrayList<>();
            if(likeList.contains(username)) {
                likeList.removeIf(i -> i.equals(username));
            } else {
                likeList.add(username);
            }
            recipes.setLikes(likeList.size());
            recipes.setLikesList(likeList);
            recipesRepository.save(recipes);
            return recipes;
        }
        throw new TummyException("recipe not found.");
    }

    @Override
    public Recipes postRating(String id, Rating rating) throws TummyException {
        Optional<Recipes> optionalRecipes = recipesRepository.findById(id);
        if(optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            List<Rating> ratings = recipes.getRatings() != null ? recipes.getRatings() : new ArrayList<>();
            ratings = ratings
                      .stream()
                      .filter(i -> i.getUsername().equals(rating.getUsername()))
                      .collect(Collectors.toList());
            ratings.clear();
            ratings.add(rating);
            recipes.setRatings(ratings);
            calculateRatings(recipes);
            recipesRepository.save(recipes);
            return recipes;
        }
        throw new TummyException("recipe not found.");
    }

    @Override
    public Recipes deleteRating(String username, String id) throws TummyException {
        Optional<Recipes> optionalRecipes = recipesRepository.findById(id);
        if(optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            List<Rating> ratings = recipes.getRatings() != null ? recipes.getRatings() : new ArrayList<>();
            ratings.removeIf(i -> i.getUsername().equals(username));
            recipes.setRatings(ratings);
            calculateRatings(recipes);
            recipesRepository.save(recipes);
            return recipes;
        }
        throw new TummyException("recipe not found.");
    }

    @Override
    public Recipes reportAbuse(String username, String id) throws TummyException {
        Optional<Recipes> optionalRecipes = recipesRepository.findById(id);
        if(optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            List<String> abusiveReportList = recipes.getAbusiveReportList() != null ? recipes.getAbusiveReportList() : new ArrayList<>();
            if(abusiveReportList.contains(username)) {
                throw new TummyException("already abuse reported");
            }
            abusiveReportList.add(username);
            recipes.setAbusive(true);
            recipes.setAbusiveReportList(abusiveReportList);
            recipesRepository.save(recipes);
            return recipes;
        }
        throw new TummyException("recipe not found.");
    }

    @Override
    public Recipes postComment(String id, Comments comments) throws TummyException {
        Optional<Recipes> optionalRecipes = recipesRepository.findById(id);
        if(optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            List<Comments> commentsList = recipes.getComments() != null ? recipes.getComments() : new ArrayList<>();
            comments.setCommentedTime(new Date(System.currentTimeMillis()));
            commentsList.add(comments);
            recipes.setComments(commentsList);
            recipes.setCommentsCount(commentsList.size());
            recipesRepository.save(recipes);
            return recipes;
        }
        throw new TummyException("recipe not found.");
    }

    @Override
    public Recipes clearAbuse(String username, String id) throws TummyException {
        TummyUser tummyUser = tummyUserRepository.findByUsername(username);
        if(tummyUser != null) {
            if(tummyUser.getRoles().contains(TummyDatatypes.Roles.ADMIN) ||
                    tummyUser.getRoles().contains(TummyDatatypes.Roles.GROUP_LEADER)) {
                Optional<Recipes> optionalRecipes = recipesRepository.findById(id);
                if(optionalRecipes.isPresent()) {
                    Recipes recipes = optionalRecipes.get();
                    recipes.setAbusive(false);
                    recipes.setAbusiveReportList(new ArrayList<>());
                    recipesRepository.save(recipes);
                    return recipes;
                }
                throw new TummyException("recipe not found.");

            }
            throw new TummyException("user does not have admin privileges");
        }
        throw new TummyException("user not found.");
    }

    @Override
    public List<Recipes> findFriendsRecipes(List<Friend> friends, Integer page) {
        List<String> usernames = friends
                                .stream()
                                .map(i -> i.getUsername())
                                .collect(Collectors.toList());
        List<Recipes> recipes = recipesRepository
                                .findByUsernameIn(usernames,
                                        PageRequest.of(page, 30, Sort.Direction.DESC, "updated_date"));
        recipes.removeIf(i -> i.getAbusive() == true);
        return recipes;
    }

    @Override
    public List<Recipes> findRecipeByIngridents(List<String> ingridents, Integer page, String cuisine, Long cookingTime) {
        List<Recipes> recipes = new ArrayList<>();
        if(cuisine != null) {
            recipes = recipesRepository.findByCuisine(cuisine,
                    PageRequest.of(page, 30, Sort.Direction.DESC, "updated_date"));
        } else {
            recipesRepository
                .findAll(PageRequest.of(page, 30, Sort.Direction.DESC, "updated_date"))
                .forEach(recipes::add);
        }
        if(ingridents != null) {
            recipes = recipes
                    .parallelStream()
                    .filter(i -> !i.getAbusive() && i.getIngridents()
                            .stream()
                            .map(j -> j.getName().toLowerCase())
                            .collect(Collectors.toList()).containsAll(ingridents)
                    ).collect(Collectors.toList());
        }
        if(cookingTime != null) {
            recipes.removeIf(i -> i.getCookingTime() > cookingTime);
        }
        return recipes;
    }
}
