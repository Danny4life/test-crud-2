package com.osiki.democrud2.service.impl;

import com.osiki.democrud2.controller.UserController;
import com.osiki.democrud2.entity.UserEntity;
import com.osiki.democrud2.model.UserModel;
import com.osiki.democrud2.repository.UserRepository;
import com.osiki.democrud2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserModel registerUser(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);
        userRepository.save(userEntity);
        return userModel;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserModel> userModels = userEntities
                .stream()
                .map(users -> new UserModel(

                        users.getFirstname(),
                        users.getLastname(),
                        users.getEmail()
                ))
                .collect(Collectors.toList());
        return userModels;
    }

    @Override
    public UserModel getUsersById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);
        return userModel;
    }

    @Override
    public UserModel updateUser(Long id, UserModel userModel) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setFirstname(userModel.getFirstname());
        userEntity.setLastname(userModel.getLastname());
        userEntity.setEmail(userModel.getEmail());
        userRepository.save(userEntity);
        return userModel;
    }

    @Override
    public boolean deleteUser(Long id) {

        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }
}
