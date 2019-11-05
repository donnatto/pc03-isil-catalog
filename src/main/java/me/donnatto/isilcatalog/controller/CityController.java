package me.donnatto.isilcatalog.controller;

import me.donnatto.isilcatalog.model.City;
import me.donnatto.isilcatalog.repository.CityRepository;
import me.donnatto.isilcatalog.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {

    private CityRepository cityRepository;
    private CityService cityService;

    @Autowired
    public CityController(CityRepository cityRepository, CityService cityService) {
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }


    @GetMapping("/add")
    public String addCity(Model model) {
        model.addAttribute("city", new City());
        return "cities-add";
    }

    @PostMapping("/add")
    public String saveCity(City city, Model model) {
        try {
            cityService.create(city);
            return "redirect/cities";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping
    public String getCities(Model model) {
        List<City> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);
        return "cities";
    }

}
