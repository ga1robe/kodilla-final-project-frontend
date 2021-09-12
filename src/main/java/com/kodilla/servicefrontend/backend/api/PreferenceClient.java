package com.kodilla.servicefrontend.backend.api;

import com.kodilla.servicefrontend.backend.api.domain.PreferenceCreationDto;
import com.kodilla.servicefrontend.backend.api.domain.PreferenceDto;
import com.kodilla.servicefrontend.backend.api.domain.PreferenceListDto;
import com.kodilla.servicefrontend.config.AdminConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class PreferenceClient {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public PreferenceDto getPreferenceById(final Long id){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences/" + id)
                .build().encode().toUri();

        try{
            PreferenceDto response = restTemplate.getForObject(url, PreferenceDto.class);
            return Optional.ofNullable(response).orElse(new PreferenceDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new PreferenceDto();
        }
    }

    public Integer addPreference(final PreferenceCreationDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PreferenceCreationDto> request = new HttpEntity<>(dto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences")
                .build().encode().toUri();

        try{
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public PreferenceDto updatePreference(final PreferenceDto updatingDto){

        if(updatingDto.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PreferenceDto> request = new HttpEntity<>(updatingDto, headers);

            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                    .path("/preferences")
                    .build().encode().toUri();

            try{
                return restTemplate.exchange(url, HttpMethod.PUT, request, PreferenceDto.class).getBody();
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new PreferenceDto();
            }
        }
        return new PreferenceDto();
    }

    public PreferenceListDto getAllPreferences(){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences")
                .build().encode().toUri();
        try{
            PreferenceListDto response = restTemplate.getForObject(url, PreferenceListDto.class);
            return Optional.ofNullable(response).orElse(new PreferenceListDto(new ArrayList<>()));
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new PreferenceListDto(new ArrayList<>());
        }
    }

    public PreferenceListDto getPreferencesBytrailBegin(final String trailBegin){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress() + "/preferences/")
                .queryParam("trailBegin", trailBegin)
                .build().encode().toUri();
        try{
            PreferenceListDto response = restTemplate.getForObject(url, PreferenceListDto.class);
            return Optional.ofNullable(response).orElse(new PreferenceListDto(new ArrayList<>()));
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new PreferenceListDto(new ArrayList<>());
        }
    }

    public void deletePreference(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences/" + id)
                .build().encode().toUri();
        try{
            restTemplate.delete(url);
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
