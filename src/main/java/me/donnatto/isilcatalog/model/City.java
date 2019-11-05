package me.donnatto.isilcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {
    private int cityId;
    private String name;
    private Country country;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
