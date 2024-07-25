package com.example.netflix.controllers;

import com.example.netflix.dto.request.UserRequest;
import com.example.netflix.dto.response.UserResponse;
import com.example.netflix.entities.UserEntity;
import com.example.netflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) throws Exception {
        UserResponse userResponse = userService.addUser(userRequest);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        }
        throw new Exception("ERRORE: Utente non inserito perche esiste gia");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws Exception {
        Boolean b = userService.deleteUser(id);
        if (b) {
            return ResponseEntity.ok("utente eliminato");
        }
        return ResponseEntity.ofNullable("ERRORE: Utente non trovato");
    }

    @PostMapping("/updateUser/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest ,@PathVariable int id) throws Exception {
      UserResponse userResponse=  userService.updateUser(userRequest,id);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        }
        throw new Exception("ERRORE: Utente non esiste");
    }
@GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponse> ricercaUser( @PathVariable int id) throws Exception {
        UserResponse userResponse=   userService.ricercaUser(id);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        }
        throw new Exception("ERRORE: Utente non esiste");
    }
}
