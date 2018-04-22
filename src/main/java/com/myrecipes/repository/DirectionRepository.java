package com.myrecipes.repository;

import com.myrecipes.model.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends CrudRepository<Direction, Long>
{
}
