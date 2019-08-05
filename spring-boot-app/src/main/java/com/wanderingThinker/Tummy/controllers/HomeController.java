package com.wanderingThinker.Tummy.controllers;

import com.wanderingThinker.Tummy.documents.Recipes;
import com.wanderingThinker.Tummy.services.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tummy/home")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    // /tummy/home?page=0(0 - 65536)
    @GetMapping
    public List<Recipes> getHomeContents(Principal principal, @RequestParam Integer page) {
        logger.info("fetching home contents for page no: " + page);
        return homeService.getHomeContents(principal.getName(), page);
    }

    //Fetch Recipes based on ingridents

    //Fetch users with todays birthday
}
