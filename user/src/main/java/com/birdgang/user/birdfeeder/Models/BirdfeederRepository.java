package com.birdgang.user.birdfeeder.Models;

import org.springframework.data.repository.CrudRepository;

import com.birdgang.user.user.Models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BirdfeederRepository extends CrudRepository<Birdfeeder, Integer> {

}

