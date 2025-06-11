package com.bni.bni.service;

import com.bni.bni.entity.Profile;
import com.bni.bni.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        profile.setCreatedAt(OffsetDateTime.now());
        profile.setUpdatedAt(OffsetDateTime.now());
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, Profile newProfile) {
        return profileRepository.findById(id)
                .map(profile -> {
                    profile.setFirstName(newProfile.getFirstName());
                    profile.setLastName(newProfile.getLastName());
                    profile.setPlaceOfBirth(newProfile.getPlaceOfBirth());
                    profile.setDateOfBirth(newProfile.getDateOfBirth());
                    profile.setUpdatedAt(OffsetDateTime.now());
                    return profileRepository.save(profile);
                }).orElse(null);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}