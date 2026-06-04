package net.sahil.journalApp.service;


/*
    here we are using the testing framework called Mockito which is a testing framework which creates mock
    objects for test cases.
 */

import net.sahil.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import net.sahil.journalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Disabled
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadByUserNameTests() {
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("ejvd89f934rbhfbuwse").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
