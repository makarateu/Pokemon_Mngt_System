package com.makara.pokemonsystem.Pokemon.Repository;

import com.makara.pokemonsystem.Pokemon.Entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    @Query("SELECT p FROM PokemonEntity p WHERE p.name = :name")
    PokemonEntity findPokemonByName(String name);

}
