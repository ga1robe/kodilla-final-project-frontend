package com.kodilla.servicefrontend.domain;

import lombok.*;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Charge {

    private String id = "";
    private ChargeStatus status;
    private String value;
    private String chargeDate;

    public boolean isSafeToUpdate() {
        return !id.isEmpty() && this.alwaysRequiredFieldsAreFilled();
    }

    public boolean isSafeToSave() {
        return id.isEmpty() && this.alwaysRequiredFieldsAreFilled();
    }

    private boolean alwaysRequiredFieldsAreFilled() {
        Pattern pricePattern = Pattern.compile("[0-9]+([.][0-9]{1,2})?");
        return !( status == null |
                !pricePattern.matcher(value).matches() |
                chargeDate.isEmpty() );
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", value='" + value + '\'' +
                ", chargeDate='" + chargeDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Charge)) return false;
        Charge charge = (Charge) o;
        return Objects.equals(getId(), charge.getId()) && getStatus() == charge.getStatus() && Objects.equals(getValue(), charge.getValue()) && Objects.equals(getChargeDate(), charge.getChargeDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getValue(), getChargeDate());
    }

    public boolean hasValidDate() {
        Pattern datePattern = Pattern.compile("^(2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        return datePattern.matcher(chargeDate).matches();
    }
}
