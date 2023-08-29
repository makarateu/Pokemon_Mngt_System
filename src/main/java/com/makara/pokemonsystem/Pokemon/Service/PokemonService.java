package com.makara.pokemonsystem.Pokemon.Service;

import com.makara.pokemonsystem.Pokemon.Model.Pokemon;

import java.util.List;

public interface PokemonService {

    Pokemon savePokemon(Pokemon pokemon);

    List<Pokemon> getAllPokemons();

    Pokemon getPokemonById(Long id);

    boolean deletePokemonByName(String name);

    Pokemon updatePokemon(Long id, Pokemon pokemon);

    Pokemon getPokemonByName(String name);

    List<Pokemon> savePokemonsList(List<Pokemon> pokemons);
}
