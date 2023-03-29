package com.osiki.democrud2.controller;

import com.osiki.democrud2.model.UserModel;
import com.osiki.democrud2.repository.UserRepository;
import com.osiki.democrud2.service.UserService;
import com.osiki.democrud2.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel userModel){

        userModel = userService.registerUser(userModel);

        return new ResponseEntity<>(userModel, HttpStatus.CREATED);

    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModel = userService.getAllUsers();

        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @GetMapping("/getAllUser/{id}")

    public ResponseEntity<UserModel> getUsersById(@PathVariable Long id){

        UserModel userModel = userService.getUsersById(id);
        return new ResponseEntity<>(userModel, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")

    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel userModel){
        userModel = userService.updateUser(id, userModel);

        return new ResponseEntity<>(userModel, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        boolean deleted = false;
        deleted = userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("User deleted", deleted);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
