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
        Integer countryId = city.getCountry().getCountryId();
        String countryName = city.getCountry().getName();
        City city1 = new City(name, new Country(countryId, countryName));
        cityRepository.create(city1);
    }

    public void update(City city) {
        cityRepository.update(city);
    }

    public void delete(Integer id) {
        cityRepository.delete(id);
    }
}
