package com.teamtreehouse.giflib.controller;

import ch.qos.logback.classic.pattern.MethodOfCallerConverter;
import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GifController {
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap){
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs",allGifs);
        return "home";
    }

    @RequestMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }

    @RequestMapping("/favorites")
    public String listFavorites(ModelMap modelMap) {
        List<Gif> gifFav = gifRepository.findByFavBool();
        modelMap.put("gifFav", gifFav);
        return "favorites";
    }

    @RequestMapping(value="/", params="q")
    public String listSearch(@RequestParam("q") String q, ModelMap modelMap) {
        List<Gif> gifs= gifRepository.findBySearch(q);
        modelMap.put("gifs", gifs);
        return "search";
    }
}
