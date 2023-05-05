package com.birdgang.user.stats.Models;

import org.springframework.data.repository.CrudRepository;

import com.birdgang.user.stats.Models.Stats;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StatsRepository extends CrudRepository<Stats, Integer> {

}

