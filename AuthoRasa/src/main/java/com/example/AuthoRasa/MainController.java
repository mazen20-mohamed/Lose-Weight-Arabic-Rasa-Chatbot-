package com.example.AuthoRasa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class MainController {
        @Autowired
        private UserRepository userRepository;

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
                if(bmr<=1500){
                    bmr = 1000;
                }
                else if(bmr <=2000){
                    bmr = 1500;
                }
                else if(bmr <= 2500){
                    bmr = 2000;
                }
                else if(bmr <=3000){
                    bmr = 2500;
                }
                else if(bmr <=3500){
                    bmr = 3000;
                }
                else if(bmr <=4000){
                    bmr = 3500;
                }
                else if(bmr <=4500){
                    bmr = 4000;
                }
                else if(bmr <=5000){
                    bmr = 4500;
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
                return new ResponseEntity<>(user.get().getId(), HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }

}
