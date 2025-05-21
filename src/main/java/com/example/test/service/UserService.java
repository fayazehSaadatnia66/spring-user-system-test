package com.example.test.service;

import com.example.test.dto.req.CreateUser;
import com.example.test.dto.res.ProfileResponse;
import com.example.test.dto.res.UserResponse;
import com.example.test.entity.User;
import com.example.test.repo.ProfileRepo;
import com.example.test.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepo userRepo;
    final ProfileRepo profileRepo;

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public UserResponse saveUser(CreateUser createUser) {
        User user = new User();
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        user.setPassword(createUser.getPassword());
        user.setUsername(createUser.getUsername());
        user.setFamilyName(createUser.getFamilyName());
        user.setPhone(createUser.getPhone());
        user.setEnabled(createUser.getEnabled());
        User saved = userRepo.save(user);
        UserResponse responseUser = new UserResponse();
        responseUser.setName(saved.getName());
        responseUser.setEmail(saved.getEmail());
        responseUser.setPassword(saved.getPassword());
        responseUser.setUsername(saved.getUsername());
        responseUser.setFamilyName(saved.getFamilyName());
        responseUser.setPhone(saved.getPhone());
        responseUser.setEnabled(saved.getEnabled());

        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setImageUrl(saved.getProfile().getImageUrl());
        profileResponse.setGender(saved.getProfile().getGender());
        profileResponse.setAddress(saved.getProfile().getAddress());
        profileResponse.setPhoneNumber(saved.getProfile().getPhoneNumber());
        profileResponse.setDateOfBirth(saved.getProfile().getDateOfBirth());
        responseUser.setProfile(profileResponse);

        return responseUser;

    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);

    }

    public User updateUser(Long id, User user) {
        User dbUser = getUserOrElseThrow(id);
        mapFromTo(user, dbUser);
        return saveToDb(dbUser);
    }

    private User saveToDb(User dbUser) {
        return userRepo.save(dbUser);
    }

    private void mapFromTo(User user, User dbUser) {
        dbUser.setName(user.getName());
        dbUser.setFamilyName(user.getFamilyName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhone(user.getPhone());
        dbUser.setPassword(user.getPassword());
        dbUser.setEnabled(user.getEnabled());

    }

    private User getUserOrElseThrow(Long id) {
        return userRepo.findById(id).orElseThrow();
    }


}
