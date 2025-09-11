package com.expensemanager.restapi.controller;

import com.expensemanager.restapi.dto.ProfileDTO;
import com.expensemanager.restapi.io.ProfileRequest;
import com.expensemanager.restapi.io.ProfileResponse;
import com.expensemanager.restapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.event.WindowFocusListener;

@RestController
@Slf4j
@RequiredArgsConstructor

public class AuthController {
    private final ModelMapper modelMapper;
    private final ProfileService profileService;

@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse profileCreation(@Valid @RequestBody ProfileRequest profileRequest){
        log.info( "API /register called {}", profileRequest );
        ProfileDTO profileDTO= mapToProfileDTO(profileRequest);
        log.info( "Printing the Profile DTO details {}",profileDTO );
    profileDTO= profileService.createProfile(profileDTO);
        return mapToProfileResponse(profileDTO);
    }

    private ProfileDTO mapToProfileDTO(@Valid ProfileRequest profileRequest) {
    return modelMapper.map( profileRequest, ProfileDTO.class);
    }

    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
    return modelMapper.map(profileDTO,ProfileResponse.class );
    }
}
