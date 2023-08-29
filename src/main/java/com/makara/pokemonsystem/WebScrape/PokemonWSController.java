package com.makara.pokemonsystem.WebScrape;

import com.makara.pokemonsystem.ChatGPT.ChatGPTRequest;
import com.makara.pokemonsystem.ChatGPT.ChatGPTResponse;
import com.makara.pokemonsystem.Pokemon.Model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonWSController {

    @Value("${openai.url}")
    private String openAiUrl;

    @Value("${openai.model}")
    private String model;

    @Autowired
    private RestTemplate template;
    private String prompt = "this is a json list of pokemons. i want you to remove all the " +
            "id for all pokemons, and based on the pokemon's name, replace the null for the pokemon " +
            "types and abilities as String. return the answer back as a json file:";

    @GetMapping("/scrape-pokemon")
    public ResponseEntity<List<Pokemon>> scrapePokemon() {

        PokemonWS pokemonWS = new PokemonWS();
        List<Pokemon> pokemons = pokemonWS.scrapePokemonData(2);

        return ResponseEntity.ok(pokemons);
    }
}
