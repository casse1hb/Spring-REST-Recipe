package com.teamtreehouse.repository;

import com.teamtreehouse.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long>
{
    User findByUsernameIgnoreCase(String username);

    List<User> findAll();

    List<User> findByFavoritesId(Long id);
}
