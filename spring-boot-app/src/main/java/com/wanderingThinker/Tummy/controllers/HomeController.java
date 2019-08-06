package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.documents.TummyUser;
import com.wanderingThinker.Tummy.services.HomeService;
import com.wanderingThinker.Tummy.supportingdocuments.Ingrident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tummy/home")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @GetMapping
    public List<Recipes> getHomeContents(Principal principal, @RequestParam Integer page) {
        // /tummy/home?page=0(0 - 65536)
        logger.info("fetching home contents for page no: " + page);
        return homeService.getHomeContents(principal.getName(), page);
    }

    @PostMapping("/recipes")
    public List<Recipes> getRecipes(@RequestBody List<String> ingridents,
                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false) String cuisine,
                                    @RequestParam(value = "cooking-time", required = false) Long cookingTime) {
        // /tummy/home/recipes?page=0&cuisine=indian&cooking-time=300000
        logger.debug("get recipes inititated");
        logger.debug("cusine: " + cuisine);
        logger.debug("cooking time: " + cookingTime);
        logger.debug("ingridents: " + ingridents.toString());
        return homeService.getRecipesByIngridents(ingridents, page, cuisine, cookingTime);
    }

}
