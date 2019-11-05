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
    private int country_id;

    public City(String name, int country_id) {
        this.name = name;
        this.country_id = country_id;
    }
}
