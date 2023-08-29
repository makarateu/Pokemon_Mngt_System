package com.makara.pokemonsystem.Pokemon.Controller;

import com.makara.pokemonsystem.Pokemon.Model.Pokemon;
import com.makara.pokemonsystem.Pokemon.Service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemons")
    public Pokemon savePokemon(@RequestBody Pokemon pokemon){
        return pokemonService.savePokemon(pokemon);
    }

    //add list of pokemons
    @PostMapping("/pokemonsList")
    public List<Pokemon> savePokemons(@RequestBody List<Pokemon> pokemons) {
        return pokemonService.savePokemonsList(pokemons);
    }

    @GetMapping("/pokemons")
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @DeleteMapping("/pokemons/{name}")
    public ResponseEntity<Map<String, Boolean>> deletePokemonByName(@PathVariable("name") String name) {
        boolean deleted = false;
        deleted  = pokemonService.deletePokemonByName(name);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/pokemons/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable("id") Long id, @RequestBody Pokemon pokemon) {
        pokemon = pokemonService.updatePokemon(id, pokemon);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/pokemons/{name}")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable("name") String name) {
        Pokemon pokemon = null;
        pokemon = pokemonService.getPokemonByName(name);
        return ResponseEntity.ok(pokemon);
    }
}
