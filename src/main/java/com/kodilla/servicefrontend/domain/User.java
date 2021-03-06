package com.kodilla.servicefrontend.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    private String id = "";
    private String name;
    private String surname;
    private String email;
    private String securePassword;
    private String registered = "";
    private Set<String> preferenceIds = new HashSet<String>();

    public boolean isSafeToUpdate() {
        return !id.isEmpty() &&
                !registered.isEmpty() &&
                this.alwaysRequiredFieldsAreFilled();
    }

    public boolean isSafeToSave() {
        return id.isEmpty() &&
                registered.isEmpty() &&
                this.alwaysRequiredFieldsAreFilled();
    }

    private boolean alwaysRequiredFieldsAreFilled() {
        Pattern emailPattern = Pattern.compile(".{3,}@.{2,}\\..{2,3}");
        return !( name.isEmpty() |
                surname.isEmpty() |
                !emailPattern.matcher(email).matches() |
                securePassword.length()<10
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", securePassword='" + securePassword + '\'' +
                ", registered='" + registered + '\'' +
                ", preferenceIds=" + preferenceIds +
                '}';
    }
}
