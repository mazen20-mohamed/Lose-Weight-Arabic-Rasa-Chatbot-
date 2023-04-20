package com.example.AuthoRasa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class MainController {
        @Autowired
        private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping(path="/register")
        public ResponseEntity<String> register (@RequestParam String userName, @RequestParam String email ,@RequestParam String password,@RequestParam String gender,@RequestParam int height,@RequestParam double weight,@RequestParam int age,@RequestParam double activationRate) {
            try {
                User n = new User();
                n.setId(userRepository.count()+1);
                n.setGender(gender);
                n.setUserName(userName);
                n.setEmail(email);
                n.setPassword(password);
                n.setAge(age);
                n.setWeight(weight);
                n.setHeight(height);
                n.setActivationRate(activationRate);
                int bmr = 0;
                // calculate calories
                if(gender.equalsIgnoreCase("men")){
                    bmr = (int)((10*weight) + (6.25*height) - (5*age) + 5);
                    bmr = (int)(bmr * activationRate);
                }
                else{
                    bmr = (int)((10*weight) + (6.25*height) - (5*age) - 161);
                    bmr = (int)(bmr * activationRate);
                }
                n.setCalories(bmr);
                userRepository.save(n);
                String string = "saved";
                return new ResponseEntity<>(string , HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping(path="/login")
        public ResponseEntity<Long> login(@RequestParam String email ,@RequestParam String password ) {
            try {
                Optional<User> user = userRepository.findByEmailAndPassword(email,password);
                LoginUpdate loginUpdate = new LoginUpdate();
                loginUpdate.setId((loginRepository.count()+1));
                loginUpdate.setTime_taken(LocalDateTime.now());
                loginUpdate.setUser(user.get());
                loginRepository.save(loginUpdate);
                user.get().addInListLogin(loginUpdate);
                userRepository.save(user.get());
                return new ResponseEntity<>(user.get().getId(), HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }
}
