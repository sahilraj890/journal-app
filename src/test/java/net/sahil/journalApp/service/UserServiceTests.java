package net.sahil.journalApp.service;

import net.sahil.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
    Here we're simply writing test where dependencies are loaded similar to as we run the
    spring application. Which fetches data from db and takes time to load when our application grows.
 */
@Disabled
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepo userRepo;

    @Disabled
    @Test
    public void testFindByUserName() {
        assertNotNull(userRepo.findByUserName("Ram"));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
        "Sahil",
        "Ram",
        "Shyam"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepo.findByUserName(name), "failed for: " + name);
    }
}
