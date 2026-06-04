package net.sahil.journalApp.Repository;


import net.sahil.journalApp.repository.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
    public void testFindByUserName() {
        userRepo.getUsersForSA();
    }
}
