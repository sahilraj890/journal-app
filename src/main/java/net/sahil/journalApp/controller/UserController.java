package net.sahil.journalApp.controller;



import net.sahil.journalApp.api.response.WeatherResponse;
import net.sahil.journalApp.entity.User;
import net.sahil.journalApp.repository.UserRepo;
import net.sahil.journalApp.service.UserService;
import net.sahil.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo repo;

    @Autowired
    private WeatherService weatherService;



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String oldUserName = authentication.getName();
        User user1 = userService.findByUserName(oldUserName);
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        userService.saveNewUser(user1);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEntry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greetings = "";
        if (weatherResponse != null) {
            greetings = " Weather feels like: " + weatherResponse.getCurrent().getTemperature();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greetings, HttpStatus.OK);
    }

}
