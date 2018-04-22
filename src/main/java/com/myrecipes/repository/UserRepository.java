package com.myrecipes.repository;

import com.myrecipes.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsernameIgnoreCase(String username);

    List<User> findAll();

    List<User> findByFavoritesId(Long id);
}
