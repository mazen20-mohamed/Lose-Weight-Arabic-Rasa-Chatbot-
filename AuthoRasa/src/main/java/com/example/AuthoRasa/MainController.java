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
        public ResponseEntity<String> register (@RequestParam String name, @RequestParam String email ,@RequestParam String password ) {
            try {
                User n = new User();
                n.setId(userRepository.count()+1);
                n.setName(name);
                n.setEmail(email);
                n.setPassword(password);
                userRepository.save(n);
                return new ResponseEntity<>("saved" , HttpStatus.OK);
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
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
}

