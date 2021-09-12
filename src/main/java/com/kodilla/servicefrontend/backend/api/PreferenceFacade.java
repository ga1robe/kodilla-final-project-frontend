package com.kodilla.servicefrontend.backend.api;

import com.kodilla.servicefrontend.domain.Preference;

import com.kodilla.servicefrontend.backend.api.mapper.PreferenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PreferenceFacade {
    private final PreferenceClient preferenceClient;
    private final PreferenceMapper preferenceMapper;

    public Preference getPreferenceById(final Long id) {
        return preferenceMapper.mapToPreference( preferenceClient.getPreferenceById(id) );
    }

    public Integer addPreference(Preference preference) {
        return preferenceClient.addPreference( preferenceMapper.mapToPreferenceDto(preference) );
    }

    public Preference updatePreference(Preference preference) {
        return preferenceMapper.mapToPreference(preferenceClient.updatePreference(preferenceMapper.mapToDto(preference)));
    }

    public List<Preference> getAllPreferences() {
        System.out.println("TEST: PreferenceFacade::getAllPreferences: " + preferenceClient.getAllPreferences());
        System.out.println("TEST: PreferenceFacade::getAllPreferences::size?: " + preferenceClient.getAllPreferences().getPreferences().size());
        return preferenceMapper.mapToPreferenceListFromPreferenceListDto( preferenceClient.getAllPreferences());
    }

    public List<Preference> getPreferencesByDestinationPoint(final String point) {
        return preferenceMapper.mapToPreferenceListFromPreferenceListDto( preferenceClient.getPreferencesBytrailBegin(point));
    }

    public void deletePreference(final Long id) {
        preferenceClient.deletePreference(id);
    }
}
