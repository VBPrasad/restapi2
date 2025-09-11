package com.expensemanager.restapi.service.Impl;

import com.expensemanager.restapi.dto.ExpenseDTO;
import com.expensemanager.restapi.dto.ProfileDTO;
import com.expensemanager.restapi.entity.ExpensesEntity;
import com.expensemanager.restapi.entity.ProfileEntity;
import com.expensemanager.restapi.repos.ProfileRepository;
import com.expensemanager.restapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;


    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
       ProfileEntity  profileEntity= mapToProfileEntity(profileDTO);
       profileEntity.setProfileId( UUID.randomUUID().toString() );
       profileEntity =profileRepository.save(profileEntity);
       log.info("Printing the profile netity details {}", profileEntity);
       return mapToProfileDTO(profileEntity);

    }



    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return  modelMapper.map(profileEntity, ProfileDTO.class );
    }

    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class );
    }
}
