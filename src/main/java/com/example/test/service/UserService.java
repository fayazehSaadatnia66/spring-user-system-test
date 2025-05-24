package com.example.test.service;

import com.example.test.dto.req.CreateProfile;
import com.example.test.dto.req.CreateUser;
import com.example.test.dto.res.ProfileResponse;
import com.example.test.dto.res.UserResponse;
import com.example.test.entity.Profile;
import com.example.test.entity.User;
import com.example.test.repo.ProfileRepo;
import com.example.test.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepo userRepo;
    final ProfileRepo profileRepo;

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = getUserDto(user);
        if (user.getProfile() != null) {
            mapUserToProfileDto(user, userResponse);
        }
        return userResponse;
    }

    public UserResponse saveUser(CreateUser createUser) {
        Profile profile = null;
        if (createUser.getProfile() != null) {
            profile = mapUserDtoToProfileDto(createUser);
        }

        User user = getUser(createUser, profile);

        User saved = userRepo.save(user);

        return convertToUserResponse(saved);
    }

    private static void mapUserToProfileDto(User saved, UserResponse responseUser) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(saved.getProfile().getId());
        profileResponse.setImageUrl(saved.getProfile().getImageUrl());
        profileResponse.setPhoneNumber(saved.getProfile().getPhoneNumber());
        profileResponse.setAddress(saved.getProfile().getAddress());
        profileResponse.setDateOfBirth(saved.getProfile().getDateOfBirth());
        profileResponse.setGender(saved.getProfile().getGender());
        responseUser.setProfile(profileResponse);
    }

    private static UserResponse getUserDto(User saved) {
        UserResponse responseUser = new UserResponse();
        responseUser.setId(saved.getId());
        responseUser.setName(saved.getName());
        responseUser.setEmail(saved.getEmail());
        responseUser.setPassword(saved.getPassword());
        responseUser.setUsername(saved.getUsername());
        responseUser.setFamilyName(saved.getFamilyName());
        responseUser.setPhone(saved.getPhone());
        responseUser.setEnabled(saved.getEnabled());
        return responseUser;
    }

    private static User getUser(CreateUser createUser, Profile profile) {
        User user = new User(
                profile,
                createUser.getUsername(),
                createUser.getPassword(),
                createUser.getName(),
                createUser.getFamilyName(),
                createUser.getEmail(),
                createUser.getPhone(),
                createUser.getEnabled()
        );
        return user;
    }

    private static Profile mapUserDtoToProfileDto(CreateUser createUser) {
        Profile profile;
        CreateProfile createProfile = createUser.getProfile();
        profile = new Profile(
                createProfile.getImageUrl(),
                createProfile.getPhoneNumber(),
                createProfile.getAddress(),
                createProfile.getDateOfBirth(),
                createProfile.getGender()
        );
        return profile;
    }


    public void deleteUser(Long id) {
        userRepo.deleteById(id);

    }
    public UserResponse updateUser(Long id, User updateUser) {
        User user = getUserById(id);
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());
        user.setUsername(updateUser.getUsername());
        user.setFamilyName(updateUser.getFamilyName());
        user.setPhone(updateUser.getPhone());
        user.setEnabled(updateUser.getEnabled());
        if (updateUser.getProfile() != null) {
            if (user.getProfile() != null) {
                Profile profile = user.getProfile();
                profile.setImageUrl(updateUser.getProfile().getImageUrl());
                profile.setPhoneNumber(updateUser.getProfile().getPhoneNumber());
                profile.setAddress(updateUser.getProfile().getAddress());
                profile.setDateOfBirth(updateUser.getProfile().getDateOfBirth());
                profile.setGender(updateUser.getProfile().getGender());
            } else {
                Profile newProfile = mapUpdateUserToProfile(updateUser.getProfile());
            }
        }
        User UpdateUser = userRepo.save(user);

        return convertToUserResponse(UpdateUser);
    }


    private Profile mapUpdateUserToProfile(Profile createProfile) {
        return new Profile(
                createProfile.getImageUrl(),
                createProfile.getPhoneNumber(),
                createProfile.getAddress(),
                createProfile.getDateOfBirth(),
                createProfile.getGender()
        );
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
