package fr.eql.ai116.linus.wattelse.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RegisterUserInDto implements Serializable {
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String phone;
    private LocalDate birthDate;
    private double latitude;
    private double longitude;
    private String addressDisplay;

    public RegisterUserInDto(String email, String password, String lastName, String firstName, String phone, LocalDate birthDate, double latitude, double longitude, String addressDisplay) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressDisplay = addressDisplay;
    }

    public RegisterUserInDto() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddressDisplay() {
        return addressDisplay;
    }

    public void setAddressDisplay(String addressDisplay) {
        this.addressDisplay = addressDisplay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "RegisterUserInDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", addressDisplay='" + addressDisplay + '\'' +
                '}';
    }
}
