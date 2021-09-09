package com.kodilla.servicefrontend.domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Preference {

    private String id="";
    private String userId;
    private String trailBegin;
    private String trailEnd;
    private String distance;
    private String minTemperature;

    public boolean isSafeToUpdate() {
        return id.chars().allMatch(Character::isDigit) && this.requiredFieldsAreFilled();
    }

    public boolean isSafeToSave() {
        return id.isEmpty() && this.requiredFieldsAreFilled();
    }

    private boolean requiredFieldsAreFilled() {
        return !( userId.isEmpty() |
                !userId.chars().allMatch(Character::isDigit) |
                trailBegin.isEmpty() |
                trailEnd.isEmpty() |
                minTemperature.isEmpty() |
                !minTemperature.chars().allMatch(Character::isDigit) |
                !distance.chars().allMatch(Character::isDigit)
        );
    }

    @Override
    public String toString() {
        return "Preference{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", trailBegin='" + trailBegin + '\'' +
                ", trailEnd='" + trailEnd + '\'' +
                ", distance='" + distance + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Preference)) return false;
        Preference that = (Preference) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getTrailBegin(), that.getTrailBegin()) && Objects.equals(getTrailEnd(), that.getTrailEnd()) && Objects.equals(getDistance(), that.getDistance()) && Objects.equals(getMinTemperature(), that.getMinTemperature());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getTrailBegin(), getTrailEnd(), getDistance(), getMinTemperature());
    }
}
