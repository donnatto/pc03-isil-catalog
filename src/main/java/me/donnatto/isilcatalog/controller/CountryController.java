package me.donnatto.isilcatalog.controller;

import me.donnatto.isilcatalog.model.Country;
import me.donnatto.isilcatalog.repository.CountryRepository;
import me.donnatto.isilcatalog.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private CountryService countryService;
    private CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryService countryService, CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/add")
    public String addCountry(Model model) {
        model.addAttribute("country", new Country());
        return "countries-add";
    }

    @PostMapping("/add")
    public String saveCountry(Country country, Model model) {
        try {
            countryService.create(country);
            return "redirect:/countries";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/edit/{id_country}")
    public String getForUpdate(@PathVariable Integer id_country, Model model) {
        Country country = countryRepository.findById(id_country);
        model.addAttribute("country", country);
        return "countries-edit";
    }

    @PostMapping("/edit/{id_country}")
    public String updateCountry(@PathVariable Integer id_country, Country country) {
        countryService.update(country);
        return "redirect:/countries";
    }

    @GetMapping
    public String getCountries(Model model) {
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "countries";
    }

    @GetMapping("/delete/{id_country}")
    public String deleteCountry(@PathVariable Integer id_country) {
        countryService.delete(id_country);
        return "redirect:/countries";
    }
}
