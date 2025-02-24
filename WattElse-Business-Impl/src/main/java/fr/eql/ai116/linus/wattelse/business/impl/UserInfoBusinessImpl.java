package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.UserInfoBusiness;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.entity.dto.UserOutDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;

@Remote(UserInfoBusiness.class)
@Stateless
public class UserInfoBusinessImpl implements UserInfoBusiness {

    private static final Logger logger = LogManager.getLogger();

    @EJB
    UserDao userDao;


    @Override
    public UserOutDto getUserInfoById(long id) {


        User user = userDao.findUserById(id);

        logger.info("user = "+user);

        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String phone = user.getPhone();

        LocalDate birthDate = user.getBirthDate();

        String addressDisplay = user.getAddressDisplay();

        return new UserOutDto
                (email, firstName, lastName, phone, birthDate, addressDisplay);

    }
}
