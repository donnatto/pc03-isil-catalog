package me.donnatto.isilcatalog.services;

import me.donnatto.isilcatalog.model.Country;
import me.donnatto.isilcatalog.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void create(Country country) {
        String name = country.getName();
        Country country1 = new Country(name);
        countryRepository.create(country1);
    }

    public void update(Country country) {
        countryRepository.update(country);
    }

    public void delete(Integer id) {
        countryRepository.delete(id);
    }
}
