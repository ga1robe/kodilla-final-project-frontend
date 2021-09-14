package com.kodilla.servicefrontend.backend.api.mapper;

import com.kodilla.servicefrontend.backend.api.UserClient;
import com.kodilla.servicefrontend.backend.api.domain.PreferenceCreationDto;
import com.kodilla.servicefrontend.backend.api.domain.PreferenceDto;
import com.kodilla.servicefrontend.backend.api.domain.PreferenceListDto;
import com.kodilla.servicefrontend.domain.Preference;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PreferenceMapper {
    private final UserClient userClient;

    public List<Preference> mapToPreferenceListFromPreferenceListDto(PreferenceListDto dto) {
        return this.mapToPreferenceList( dto.getPreferences() );
    }

    public Preference mapToPreference(PreferenceDto dto){
        return Preference.builder()
                .id( dto.getId().toString() )
                .trailBegin( dto.getTrailBegin() )
                .trailEnd( dto.getTrailEnd() )
                .minTemperature( dto.getMinTemperature().toString() )
                .distance( dto.getDistance().toString() )
                .userId( dto.getUserId().toString() )
                .build();
    }

    public List<Preference> mapToPreferenceList(List<PreferenceDto> dtoList) {
        System.out.println("TEST. dtoList(size)" + dtoList.size());
        return dtoList.stream()
                .map(this::mapToPreference)
                .collect(Collectors.toList());
    }

    public PreferenceDto mapToDto(Preference preference) {
        return PreferenceDto.builder()
                .id( (preference.getId().equals(""))? null : Long.parseLong(preference.getId()) )
                .trailBegin( preference.getTrailBegin() )
                .trailEnd( preference.getTrailEnd() )
                .minTemperature( Integer.parseInt( preference.getMinTemperature() ) )
                .distance( BigInteger.valueOf( Integer.parseInt( preference.getDistance() ) ) )
                .userId( userClient.getUserById(Long.parseLong(preference.getUserId())).getId() )
                .build();
    }

    public PreferenceCreationDto mapToPreferenceDto(Preference preference) {
        return PreferenceCreationDto.builder()
                .trailBegin( preference.getTrailBegin() )
                .trailEnd( preference.getTrailEnd() )
                .minTemperature( Integer.parseInt( preference.getMinTemperature() ) )
                .minTemperature( Integer.valueOf(  preference.getMinTemperature() ) )
                .userId( Long.parseLong( preference.getUserId() ) )
                .build();
    }
}
