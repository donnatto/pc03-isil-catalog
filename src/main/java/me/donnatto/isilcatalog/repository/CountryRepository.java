package me.donnatto.isilcatalog.repository;

import me.donnatto.isilcatalog.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CountryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Country country) {
        final String sql = "INSERT INTO country (name) VALUES (?)";
        jdbcTemplate.update(sql, country.getName());
    }

    public void update(Country country) {
        final String sql = "UPDATE country SET name = ? WHERE country_id = ?";
        jdbcTemplate.update(sql, country.getName(), country.getCountryId());
    }

    public void delete(int id) {
        final String sql = "DELETE country WHERE country_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Country> findAll() {
        final String sql = "SELECT * FROM country";
        return jdbcTemplate.query(sql, CountryRepository::countryRowMapper);
    }

    public Country findById(Integer id) {
        final String sql = "SELECT * FROM country WHERE country_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, CountryRepository::countryRowMapper);
    }

    public static Country countryRowMapper(ResultSet resultSet, int i) throws SQLException {
        Integer cId = resultSet.getInt("country_id");
        String name = resultSet.getString("name");
        return new Country(cId, name);
    }
}
