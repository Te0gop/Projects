package io.spring.webapp.repository;

import io.spring.webapp.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Long countById(Integer id);
}
