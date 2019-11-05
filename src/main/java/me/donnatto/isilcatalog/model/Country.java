package me.donnatto.isilcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {

    private int countryId;
    private String name;

    public Country(String name) {
        this.name = name;
    }
}
