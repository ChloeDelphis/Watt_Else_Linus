package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.AccountClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.Role;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

    private Long idUtilisateur;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private String phone;

    private LocalDate birthDate;
    private LocalDate inscriptionDate;
    private LocalDate unInscriptionDate;
    private Double latitude;
    private Double longitude;
    private String addressDisplay;
    private String postalCode;
    private AccountClosingMotive accountClosingMotive;

    ///  Constructeur vide
    public User() {
    }

    ///  Constructeur surchargé

    public User(Long idUtilisateur, String email, String password, String firstName, String lastName, Role role,
                String phone, LocalDate birthDate, LocalDate inscriptionDate, LocalDate unInscriptionDate,
                Double latitude, Double longitude, String addressDisplay, String postalCode, AccountClosingMotive
                        accountClosingMotive) {
        this.idUtilisateur = idUtilisateur;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phone = phone;
        this.birthDate = birthDate;
        this.inscriptionDate = inscriptionDate;
        this.unInscriptionDate = unInscriptionDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressDisplay = addressDisplay;
        this.postalCode = postalCode;
        this.accountClosingMotive = accountClosingMotive;
    }

    ///  Getters


    public AccountClosingMotive getAccountClosingMotive() {return accountClosingMotive;}

    public String getAddressDisplay() {
        return addressDisplay;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public LocalDate getUnInscriptionDate() {
        return unInscriptionDate;
    }

    public LocalDate getInscriptionDate() {
        return inscriptionDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getPostalCode() {
        return postalCode;
    }

    ///  Setters

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setInscriptionDate(LocalDate inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public void setUnInscriptionDate(LocalDate unInscriptionDate) {
        this.unInscriptionDate = unInscriptionDate;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setAddressDisplay(String addressDisplay) {
        this.addressDisplay = addressDisplay;
    }

    public void setAccountClosingMotive(AccountClosingMotive accountClosingMotive) {
        this.accountClosingMotive = accountClosingMotive;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    ///  Méthodes

    @Override
    public String toString() {
        return "User{" +
                "idUtilisateur=" + idUtilisateur +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", inscriptionDate=" + inscriptionDate +
                ", unInscriptionDate=" + unInscriptionDate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", addressDisplay='" + addressDisplay + '\'' +
                ", postalCode=" + postalCode +
                ", accountClosingMotive=" + accountClosingMotive +
                '}';
    }

    public String getFullName(){
        String fullName = getFirstName() + " " +getLastName();
        return fullName;
    }
}
