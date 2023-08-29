package com.makara.pokemonsystem.Pokemon.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Double price;
    private String imageUrl;
    private String types;
    private String abilities;

    public PokemonEntity() {
    }

    public PokemonEntity(String name, Double price, String imageUrl, String types, String abilities) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.types = types;
        this.abilities = abilities;
    }

    public PokemonEntity(long id, String name, Double price, String imageUrl, String types, String abilities) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.types = types;
        this.abilities = abilities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return "PokemonEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", types='" + types + '\'' +
                ", abilities='" + abilities + '\'' +
                '}';
    }
}
