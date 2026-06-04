package net.sahil.journalApp.repository;

import net.sahil.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
