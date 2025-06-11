package com.bni.bni.controller;

import com.bni.bni.entity.Profile;
import com.bni.bni.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles") //TAMBAHAN apa uyyaaa
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
    //     Optional<Profile> profile = profileService.getProfileById(id);
    //     return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // @PostMapping
    // public Profile createProfile(@RequestBody Profile profile) {
    //     return profileService.createProfile(profile);
    // }

    @PostMapping("/buatprofile")
    public ResponseEntity<Map<String, Object>> createProfile(@RequestBody Map<String, String> body) {
    try {
        Long userId = Long.parseLong(body.get("user_id"));
        String firstName = body.get("first_name");
        String lastName = body.get("last_name");
        String placeOfBirth = body.get("place_of_birth");
        LocalDate dateOfBirth = LocalDate.parse(body.get("date_of_birth"));

        // Inisialisasi Profile baru
        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPlaceOfBirth(placeOfBirth);
        profile.setDateOfBirth(dateOfBirth);
        profile.setCreatedAt(OffsetDateTime.now());
        profile.setUpdatedAt(OffsetDateTime.now());

        Profile savedProfile = profileService.createProfile(profile);

        // Response
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "Profile created successfully");
        response.put("profile", savedProfile);

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("message", "Failed to create profile");
        response.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}

    // @PutMapping("/{id}")
    // public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
    //     Profile updated = profileService.updateProfile(id, profile);
    //     return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
    //     profileService.deleteProfile(id);
    //     return ResponseEntity.ok().build();
    // }
}