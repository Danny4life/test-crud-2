package com.osiki.democrud2.service;

import com.osiki.democrud2.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel registerUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUsersById(Long id);

    UserModel updateUser(Long id, UserModel userModel);

    boolean deleteUser(Long id);
}
