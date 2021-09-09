package com.kodilla.servicefrontend.backend.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreferenceDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userDto")
    private UserDto userDto;

    @JsonProperty("trailBegin")
    private String trailBegin;

    @JsonProperty("trailEnd")
    private String trailEnd;

    @JsonProperty("distance")
    private BigInteger distance;

    @JsonProperty("minTemperature")
    private Integer minTemperature;

    @Override
    public String toString() {
        return "PreferenceDto{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", trailBegin='" + trailBegin + '\'' +
                ", trailEnd='" + trailEnd + '\'' +
                ", distance=" + distance +
                ", minTemperature=" + minTemperature +
                '}';
    }
}
