package com.birdgang.user.user.Controllers;

import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.birdgang.user.user.Models.User;
import com.birdgang.user.user.Models.UserRepository;

@RestController
@RequestMapping("/api/v1/users/")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method is used to register a user
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @return HttpStatus.OK if the user was successfully registered,
     *          HttpStatus.CONFLICT if the username already exists
     */
    @GetMapping(path="/register/{username}/{password}/{firstName}/{lastName}")
    public @ResponseBody String register(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if (user.getUsername().equals(username))
                return "user_exists";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        userRepository.save(newUser);
        return "success";
    }

    /**
     * This method is used to login a user
     * @param username
     * @param password
     * @return HttpStatus.OK if the user exists and the password is correct,
     *          HttpStatus.NOT_FOUND if the user does not exist,
     *          HttpStatus.UNAUTHORIZED if the password is incorrect
     */
    @GetMapping(path="/login/{username}/{password}")
    public @ResponseBody String login(@PathVariable String username, @PathVariable String password){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password))
                    return "success";
                else
                    return "wrong_password";
            }
        }
        return "userDNE";
    }

    /**
     * Change a users username
     * 
     * @param oldUsername The users current username
     * @param newUsername The users new username
     * @return A string telling if it was a successful change or that the username does not exist
     */
    @GetMapping(path="/username/{oldUsername}/{newUsername}")
    public @ResponseBody String changeUsername(@PathVariable("oldUsername") String oldUsername, @PathVariable("newUsername") String newUsername){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if (user.getUsername().equals(oldUsername)){
                user.setUsername(newUsername);
                userRepository.save(user);
                return "Username successfully changed!";
            }
        }
        return "That username does not exist.";
    }

    /**
     * Change a users password
     * 
     * @param username The users current username
     * @param newPassword The users new password
     * @return A string telling if it was a successful change or that the username does not exist
     */
    @GetMapping(path="/password/{username}/{newPassword}")
    public @ResponseBody String changePassowrd(@PathVariable("username") String username, @PathVariable("newPassword") String newPassword){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if (user.getUsername().equals(username)){
                user.setPassword(newPassword);
                userRepository.save(user);
                return "Password successfully changed!";
            }
        }
        return "That username does not exist.";
    }

    /**
     * Change a users First Name
     * 
     * @param username The users current username
     * @param newFirstName The users new password
     * @return A string telling if it was a successful change or that the username does not exist
     */
    @GetMapping(path="/firstname/{username}/{newFirstName}")
    public @ResponseBody String changeFirstName(@PathVariable("username") String username, @PathVariable("newFirstName") String newFirstName){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if (user.getUsername().equals(username)){
                user.setFirstName(newFirstName);
                userRepository.save(user);
                return "First Name successfully changed!";
            }
        }
        return "That username does not exist.";
    }

    /**
     * Change a users Last name
     * 
     * @param username The users current username
     * @param newLastName The users new password
     * @return A string telling if it was a successful change or that the username does not exist
     */
    @GetMapping(path="/lastname/{username}/{newFirstName}")
    public @ResponseBody String changeLastName(@PathVariable("username") String username, @PathVariable("newLastName") String newLastName){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if (user.getUsername().equals(username)){
                user.setLastName(newLastName);
                userRepository.save(user);
                return "Last Name successfully changed!";
            }
        }
        return "That username does not exist.";
    }

    /**
     * remove a user
     * 
     * @param username The users current username
     * @return A string telling if it was a successful remove or that the username does not exist
     */
    @GetMapping(path="/remove/{username}")
    public @ResponseBody String removeUser(@PathVariable("username") String username){
        Iterable<User> userRepo = getAllUsers();
        for (User user : userRepo) {
            if (user.getUsername().equals(username)){
                userRepository.delete(user);
                return "User successfully removed!";
            }
        }
        return "That username does not exist.";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id){
        return new User();
    }

    public UserRepository getRepo(){
        return this.userRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}

