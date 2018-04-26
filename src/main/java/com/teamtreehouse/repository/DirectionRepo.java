package com.teamtreehouse.repository;

import com.teamtreehouse.model.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepo extends CrudRepository<Direction, Long>
{
}
