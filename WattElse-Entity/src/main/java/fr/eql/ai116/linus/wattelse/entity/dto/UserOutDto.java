package fr.eql.ai116.linus.wattelse.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UserOutDto implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    private LocalDate birthDate;

    private String addressDisplay;

    /// Constructors
    public UserOutDto() {
    }

    public UserOutDto(String email, String firstName, String lastName, String phone, LocalDate birthDate, String addressDisplay) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.addressDisplay = addressDisplay;
    }

    /// Getters
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddressDisplay() {
        return addressDisplay;
    }
    /// Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddressDisplay(String addressDisplay) {
        this.addressDisplay = addressDisplay;
    }
}
