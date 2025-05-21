package com.example.test.service;

import com.example.test.entity.Profile;
import com.example.test.repo.ProfileRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ProfileService {
    final ProfileRepo profileRepo;
    public ProfileService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

public Profile getProfileById(Long id) {
    return profileRepo.findById(id).orElseThrow();
}
public Profile saveProfile(Profile profile) {
    return profileRepo.save(profile);
}
public Profile updateProfile(Long id, Profile profile) {
    Profile dbProfile =  getOrElseThrow(id);
    mapFormTo(profile, dbProfile);
    return saveToDb(dbProfile);
}

    private Profile getOrElseThrow(Long id) {
       return profileRepo.findById(id).orElseThrow();

    }
    private void mapFormTo(Profile profile,Profile dbProfile) {
    dbProfile.setImageUrl(profile.getImageUrl());
    dbProfile.setPhoneNumber(profile.getPhoneNumber());
    dbProfile.setAddress(profile.getAddress());
    dbProfile.setGender(profile.getGender());
    dbProfile.setDateOfBirth(profile.getDateOfBirth());
    }
    private Profile saveToDb(Profile profile) {
    return profileRepo.save(profile);
    }
}

