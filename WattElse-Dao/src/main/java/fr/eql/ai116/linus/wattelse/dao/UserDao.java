package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.dao.exceptions.UserAlreadyExistException;
import fr.eql.ai116.linus.wattelse.entity.Role;
import fr.eql.ai116.linus.wattelse.entity.pojo.Session;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;

public interface UserDao {
    Session findSession(String token);
    void updateSession(String token, long userId);
    Role findRoleById(long id);
    User authenticate(String email, String password);
    void registerNewUser(User user) throws UserAlreadyExistException;
    User getUserByStation(long idStation);
    User findUserById(long idUser);
}
