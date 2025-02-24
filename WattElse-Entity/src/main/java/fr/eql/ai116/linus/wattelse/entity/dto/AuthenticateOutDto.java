package fr.eql.ai116.linus.wattelse.entity.dto;

import java.io.Serializable;

public class AuthenticateOutDto implements Serializable {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String token;

    public AuthenticateOutDto(long id, String firstName, String lastName, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }
}
