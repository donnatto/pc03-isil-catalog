package me.donnatto.isilcatalog.services;

import me.donnatto.isilcatalog.model.City;
import me.donnatto.isilcatalog.model.Country;
import me.donnatto.isilcatalog.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void create(City city) {
        String name = city.getName();
        Integer countryId = city.getCountry_id();
        City city1 = new City(name, countryId);
        cityRepository.create(city1);
    }
}
