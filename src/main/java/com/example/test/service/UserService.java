package com.example.test.service;

import com.example.test.entity.Person;
import com.example.test.entity.Profile;
import com.example.test.entity.User;
import com.example.test.repo.ProfileRepo;
import com.example.test.repo.UserRepo;
import lombok.AllArgsConstructor;
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

    public User saveUser(User user) {

        User dbUser = userRepo.save(user);
        Profile profile = new Profile();
        Profile dbProfile = profileRepo.save(profile);
//        Profile dbProfile = profileRepo.save(user.getProfile());

//        dbUser.setProfile(dbProfile);
        dbUser.setProfile(dbProfile);
        return dbUser;
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
