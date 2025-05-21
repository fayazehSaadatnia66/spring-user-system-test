package com.example.test.controller;

import com.example.test.entity.Profile;
import com.example.test.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    final ProfileService profileService;

    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @PostMapping
    public Profile addProfile(@RequestBody Profile profile) {
        return profileService.saveProfile(profile);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        return profileService.updateProfile(id, profile);
    }
}
