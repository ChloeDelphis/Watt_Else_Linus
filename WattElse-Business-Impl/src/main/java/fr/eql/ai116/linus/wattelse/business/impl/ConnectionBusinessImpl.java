package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.ConnectionBusiness;
import fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils;
import fr.eql.ai116.linus.wattelse.dao.exceptions.UserAlreadyExistException;
import fr.eql.ai116.linus.wattelse.business.exceptions.UserRegistrationException;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.entity.Role;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;

@Remote(ConnectionBusiness.class)
@Stateless
public class ConnectionBusinessImpl implements ConnectionBusiness {

    @EJB
    private UserDao userDao;

    @Override
    public void registerNewUser(String email, String password, String firstName, String lastName, String phone, LocalDate birthDate,double latitude, double longitude, String addressDisplay) throws UserRegistrationException {
        User user = new User(
                -1L,
                email,
                password,
                firstName,
                lastName,
                Role.USER,
                phone,
                birthDate,
                LocalDate.now(),
                null,
                latitude,
                longitude,
                addressDisplay,
                AddressUtils.getPostalCode(addressDisplay),
                null
        );
        try {
            userDao.registerNewUser(user);
        } catch (UserAlreadyExistException e) {
            throw new UserRegistrationException(e.getMessage());
        }
    }
}
