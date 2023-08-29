package com.makara.pokemonsystem.Pokemon.Service;

import com.makara.pokemonsystem.Pokemon.Entity.PokemonEntity;
import com.makara.pokemonsystem.Pokemon.Model.Pokemon;
import com.makara.pokemonsystem.Pokemon.Repository.PokemonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServceImp implements PokemonService{

    private PokemonRepository pokemonRepository;

    public PokemonServceImp(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        BeanUtils.copyProperties(pokemon, pokemonEntity);
        pokemonRepository.save(pokemonEntity);
        return pokemon;
    }

    @Override
    public List<Pokemon> getAllPokemons() {
        List<PokemonEntity> pokemonEntities = pokemonRepository.findAll();
        List<Pokemon> pokemons = pokemonEntities
                .stream()
                .map(pokemonEntity -> new Pokemon(
                    pokemonEntity.getId(),
                    pokemonEntity.getName(),
                    pokemonEntity.getPrice(),
                    pokemonEntity.getImageUrl(),
                    pokemonEntity.getTypes(),
                    pokemonEntity.getAbilities()
                ))
                .collect(Collectors.toList());

        return pokemons;
    }

    @Override
    public boolean deletePokemonByName(String name) {
        PokemonEntity pokemon = pokemonRepository.findPokemonByName(name);
        pokemonRepository.delete(pokemon);
        return true;
    }

    @Override
    public Pokemon updatePokemon(Long id, Pokemon pokemon) {
        PokemonEntity pokemonEntity = pokemonRepository.findById(id).get();
        pokemonEntity.setName(pokemon.getName());
        pokemonEntity.setPrice(pokemon.getPrice());
        pokemonEntity.setTypes(pokemon.getTypes());
        pokemonEntity.setImageUrl(pokemon.getImageUrl());
        pokemonEntity.setAbilities(pokemon.getAbilities());

        pokemonRepository.save(pokemonEntity);
        return pokemon;
    }

    @Override
    public Pokemon getPokemonById(Long id) {
        PokemonEntity pokemonEntity = pokemonRepository.findById(id).get();
        Pokemon pokemon = new Pokemon();
        BeanUtils.copyProperties(pokemonEntity, pokemon);
        return pokemon;
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        PokemonEntity pokemonEntity = pokemonRepository.findPokemonByName(name);
        Pokemon pokemon = new Pokemon();
        BeanUtils.copyProperties(pokemonEntity, pokemon);
        return pokemon;
    }

    @Override
    public List<Pokemon> savePokemonsList(List<Pokemon> pokemons) {
        for(Pokemon p: pokemons) {
            PokemonEntity pokemonEntity = new PokemonEntity();
            BeanUtils.copyProperties(p, pokemonEntity);
            pokemonRepository.save(pokemonEntity);
        }
        return pokemons;
    }
}
