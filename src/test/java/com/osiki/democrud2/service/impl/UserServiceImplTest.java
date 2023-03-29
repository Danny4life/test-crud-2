package com.osiki.democrud2.service.impl;

import com.osiki.democrud2.controller.UserController;
import com.osiki.democrud2.entity.UserEntity;
import com.osiki.democrud2.model.UserModel;
import com.osiki.democrud2.repository.UserRepository;
import com.osiki.democrud2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository);

    }


    @Test
    void canRegisterUser() {
        //given
        UserModel userModel = new UserModel();
        userModel.setFirstname("John");
        userModel.setLastname("Doe");
        userModel.setEmail("john@gmail.com");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);

        //when
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        //then
        UserModel registeredUser = underTest.registerUser(userModel);

        verify(userRepository).save(userEntity);

        assertEquals(userModel.getFirstname(), registeredUser.getFirstname());
        assertEquals(userModel.getLastname(), registeredUser.getLastname());
        assertEquals(userModel.getEmail(), registeredUser.getEmail());
    }

    @Test
    void canGetAllUsers() {
        //when
//        underTest.getAllUsers();
        // Mock the UserRepository to return a list of user entities
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity());
        when(userRepository.findAll()).thenReturn(userEntities);
         //then
//        verify(userRepository).findAll();

        // Call the getAllUsers() method and verify that it returns a list of user models
        List<UserModel> userModels = underTest.getAllUsers();
        assertEquals(userEntities.size(), userModels.size());
        for(int i = 0; i < userEntities.size(); i++){
            UserEntity userEntity = userEntities.get(i);
            UserModel userModel = userModels.get(i);
            assertEquals(userEntity.getFirstname(), userModel.getFirstname());
            assertEquals(userEntity.getLastname(), userModel.getLastname());
            assertEquals(userEntity.getEmail(), userModel.getEmail());
        }


    }

    @Test
    @Disabled
    void canGetUsersById() {

        //when
    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    @Disabled
    void deleteUser() {
    }
}