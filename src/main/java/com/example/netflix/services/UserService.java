package com.example.netflix.services;

import com.example.netflix.dto.request.UserRequest;
import com.example.netflix.dto.response.UserResponse;
import com.example.netflix.entities.UserEntity;
import com.example.netflix.mapper.UserMapper;
import com.example.netflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponse addUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.mapMoveDtoToEntity(userRequest);
        UserEntity userSave = userRepository.save(userEntity);
        return userMapper.mapMovieEntityToResponse(userSave);

    }

    public Boolean deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            UserResponse userResponse = ricercaUser(id);
            if (userResponse != null) {
                return false;
            }
            return true;
        }
        return false;
    }


    public UserResponse updateUser(UserRequest userRequest, int id) {
        UserEntity userEntity = userMapper.mapMoveDtoToEntity(userRequest);
        if (userRepository.findById(id).isPresent()) {
            userEntity.setIdUser(id);
            UserEntity userSave = userRepository.save(userEntity);
            return userMapper.mapMovieEntityToResponse(userSave);
        }
        return null;
    }

    public UserResponse ricercaUser(int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            UserResponse userResponse = userMapper.mapMovieEntityToResponse(userEntity.get());
            return userResponse;
        }
        return null;
    }
}
