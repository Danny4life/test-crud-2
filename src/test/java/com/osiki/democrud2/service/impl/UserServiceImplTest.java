package com.osiki.democrud2.service.impl;

import com.osiki.democrud2.entity.UserEntity;
import com.osiki.democrud2.model.UserModel;
import com.osiki.democrud2.repository.UserRepository;
import com.osiki.democrud2.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
        underTest.getAllUsers();
        //then
        verify(userRepository).findAll();


    }

    @Test
    @Disabled
    void getUsersById() {
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