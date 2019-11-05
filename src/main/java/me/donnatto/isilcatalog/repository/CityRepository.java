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
        jdbcTemplate.update(sql, city.getName(), city.getCountry_id());
    }

    public List<City> findAll() {
        final String sql = "SELECT * FROM city";
        return jdbcTemplate.query(sql, CityRepository::cityRowMapper);
    }

    public static City cityRowMapper(ResultSet resultSet, int i) throws SQLException {
        Integer cId = resultSet.getInt("city_id");
        String name = resultSet.getString("name");
        Integer countryId = resultSet.getInt("country_id");

        return new City(cId, name, countryId);
    }
}
