package me.donnatto.isilcatalog.repository;

import me.donnatto.isilcatalog.model.City;
import me.donnatto.isilcatalog.model.Country;
import me.donnatto.isilcatalog.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CityRepository {

    private JdbcTemplate jdbcTemplate;
    private CountryRepository countryRepository;

    @Autowired
    public CityRepository(JdbcTemplate jdbcTemplate, CountryRepository countryRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.countryRepository = countryRepository;
    }

    public void create(City city) {
        final String sql = "INSERT INTO city (name, country_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, city.getName(), city.getCountry().getCountryId());
    }

    public void update(City city) {
        final String sql = "UPDATE city SET name = ?, country_id = ? WHERE city_id = ?";
        jdbcTemplate.update(sql, city.getName(), city.getCountry().getCountryId(), city.getCityId());
    }

    public void delete(int id) {
        final String sql = "DELETE city WHERE city_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<City> findAll() {
        final String sql = "SELECT * FROM city INNER JOIN country ON country.country_id = city.country_id";
        return jdbcTemplate.query(sql, CityRepository::cityRowMapper);
    }

    public City findById(Integer id) {
        final String sql = "SELECT * FROM city INNER JOIN country ON country.country_id = city.country_id WHERE " +
                "city_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, CityRepository::cityRowMapper);
    }

    public static City cityRowMapper(ResultSet resultSet, int i) throws SQLException {
        Integer cId = resultSet.getInt("city_id");
        String name = resultSet.getString("name");
        Integer countryId = resultSet.getInt("country_id");
        String countryName = resultSet.getString("country.name");
        Country country = new Country(countryId, countryName);
        return new City(cId, name, country);
    }
}
