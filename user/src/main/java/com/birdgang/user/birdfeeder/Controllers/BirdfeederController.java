package com.birdgang.user.birdfeeder.Controllers;
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

import com.birdgang.user.birdfeeder.Models.Birdfeeder;
import com.birdgang.user.birdfeeder.Models.BirdfeederRepository;
import com.birdgang.user.user.Models.User;
import com.birdgang.user.user.Models.UserRepository;

@RestController
@RequestMapping("/api/v1/birdfeeder/")
public class BirdfeederController {

    @Autowired
    private BirdfeederRepository birdfeederRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Birdfeeder birdfeeder){
    }

    @GetMapping("/{id}")
    public Birdfeeder get(@PathVariable("id") long id){
        return new Birdfeeder();
    }

    @GetMapping
    public @ResponseBody Iterable<Birdfeeder> getAllbirdfeeders() {
        return birdfeederRepository.findAll();
    }

    @GetMapping(path="/add/{fkUser}/{bird}/{food}/{startTime}/{endTime}/{season}")
    public @ResponseBody String addNewUser( @PathVariable("fkUser") Integer fkUser,
                                            @PathVariable("bird") String bird,
                                            @PathVariable("food") String food,
                                            @PathVariable("startTime") String startTime,
                                            @PathVariable("endTime") String endTime,
                                            @PathVariable("season") String season){
        User u = userRepository.findById(fkUser).orElse(new User());
        Iterable<Birdfeeder> feederRepo = getAllbirdfeeders();
        int maxCurrentFeederNum = 0;
        for (Birdfeeder feeder : feederRepo) {
            if (feeder.getFKUser().getUserID().equals(fkUser)){
                if(maxCurrentFeederNum < feeder.getBirdfeederNumber()){
                    maxCurrentFeederNum = feeder.getBirdfeederNumber();
                }
            }
        }
        Birdfeeder n = new Birdfeeder();
        n.setFood(food);
        n.setBird(bird);
        n.setStartTime(startTime);
        n.setEndTime(endTime);
        n.setSeason(season);
        n.setFKUser(u);
        n.setBirdfeederNumber(maxCurrentFeederNum + 1);
        birdfeederRepository.save(n);
        return "Saved Birdfeeder";
    }

    /**
     * Change a birdfeeder settings
     * 
     * @param username The users current username
     * @param settings The birdfeeders new settings
     * @return A string telling if it was a successful change or that the username does not exist
     */
    @GetMapping(path="/settings/{username}/{bird}/{food}/{feederNum}/{startTime}/{endTime}/{season}")
    public @ResponseBody String changeSettings(@PathVariable("username") String username, @PathVariable("feederNum") Integer feederNum, 
                                            @PathVariable("bird") String bird,
                                            @PathVariable("food") String food,
                                            @PathVariable("startTime") String startTime,
                                            @PathVariable("endTime") String endTime,
                                            @PathVariable("season") String season){
        Iterable<Birdfeeder> feederRepo = getAllbirdfeeders();
        for (Birdfeeder feeder : feederRepo) {
            if (feeder.getFKUser().getUsername().equals(username)){
                if (feeder.getBirdfeederNumber().equals(feederNum)){
                    feeder.setBird(bird);
                feeder.setFood(food);
                feeder.setStartTime(startTime);
                feeder.setEndTime(endTime);
                feeder.setSeason(season);
                    birdfeederRepository.save(feeder);
                    return "Settings successfully changed!";
                }
            }
        }
        return "That username or birdfeeder does not exist.";
    }

    /**
     * Remove a users birdfeeder
     * 
     * @param username The users current username
     * @param feederNum The birdfeeders number if the user has multiple
     * @return A string telling if it was a successful change or that the username/birdfeeder does not exist
     */
    @GetMapping(path="/remove/{username}/{feederNum}")
    public @ResponseBody String remove(@PathVariable("username") String username, @PathVariable("feederNum") Integer feederNum){
        Iterable<Birdfeeder> feederRepo = getAllbirdfeeders();
        for (Birdfeeder feeder : feederRepo) {
            if (feeder.getFKUser().getUsername().equals(username)){
                if (feeder.getBirdfeederNumber().equals(feederNum)){
                    birdfeederRepository.delete(feeder);
                    return "Birdfeeder successfully removed!";
                }
            }
        }
        return "That username or birdfeeder does not exist.";
    }
}

