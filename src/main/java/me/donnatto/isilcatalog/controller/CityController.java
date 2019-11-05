package me.donnatto.isilcatalog.controller;

import me.donnatto.isilcatalog.model.City;
import me.donnatto.isilcatalog.model.Country;
import me.donnatto.isilcatalog.repository.CityRepository;
import me.donnatto.isilcatalog.repository.CountryRepository;
import me.donnatto.isilcatalog.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {

    private CityRepository cityRepository;
    private CityService cityService;
    private CountryRepository countryRepository;

    @Autowired
    public CityController(CityRepository cityRepository, CityService cityService, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.cityService = cityService;
        this.countryRepository = countryRepository;
    }


    @GetMapping("/add")
    public String addCity(Model model) {
        model.addAttribute("city", new City());
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "cities-add";
    }

    @PostMapping("/add")
    public String saveCity(City city, Model model) {
        try {
            cityService.create(city);
            return "redirect:/cities";
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

    @GetMapping("/edit/{id_city}")
    public String getForUpdate(@PathVariable Integer id_city, Model model) {
        City city = cityRepository.findById(id_city);
        model.addAttribute("city", city);
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "cities-edit";
    }

    @PostMapping("/edit/{id_city}")
    public String updateCity(@PathVariable Integer id_city, City city) {
        cityService.update(city);
        return "redirect:/cities";
    }

    @GetMapping("/delete/{id_city}")
    public String deleteCity(@PathVariable Integer id_city, Model model) {
        try {
            cityService.delete(id_city);
            return "redirect:/cities";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
