package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.business.exceptions.UserRegistrationException;

import java.time.LocalDate;

public interface ConnectionBusiness {
    void registerNewUser(String email, String password, String firstName, String lastName, String phone, LocalDate birthDate, double latitude, double longitude, String addressDisplay) throws UserRegistrationException;
}
