package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.exceptions.AuthenticationException;
import fr.eql.ai116.linus.wattelse.business.exceptions.AuthorizationException;
import fr.eql.ai116.linus.wattelse.business.SecurityBusiness;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.entity.Role;
import fr.eql.ai116.linus.wattelse.entity.pojo.Session;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.dto.AuthenticateOutDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;

@Remote(SecurityBusiness.class)
@Stateless
public class SecurityBusinessImpl implements SecurityBusiness {

    // Utiliser pour la génération de token
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    private static final int SESSION_TIME = 60 * 60 * 1000; // 60 minutes

    @EJB
    private UserDao userDao;

    @Override
    public AuthenticateOutDto authenticate(String email, String password) throws AuthenticationException {
        User user = userDao.authenticate(email, password);
        if (user == null) {
            throw new AuthenticationException("Identifiant et/ou mot de passe incorrect(s).");
        }
        String token = issueToken();
        userDao.updateSession(token, user.getIdUtilisateur());
        return new AuthenticateOutDto(user.getIdUtilisateur(), user.getFirstName(), user.getLastName(), token);
    }

    @Override
    public void authorize(String authorization, Role role) throws AuthorizationException {
        System.out.println(authorization);
        String token = authorization.substring(7);
        Session session = userDao.findSession(token);
        if (session == null) {
            throw new AuthorizationException("Pas de session correspondant au token fourni.");
        }
        if (Timestamp.from(Instant.now()).getTime() - session.getTimestamp().getTime() > SESSION_TIME) {
            throw new AuthorizationException("Session expirée.");
        }
        Role userRole = userDao.findRoleById(session.getUserId());
        checkRole(role, userRole);
    }

    private void checkRole(Role authorizedRole, Role userRole) throws AuthorizationException {
        switch (userRole) {
            case USER:
                if (authorizedRole.equals(Role.ADMIN)) {
                    throw new AuthorizationException("Rôle insuffisant.");
                }
            case ADMIN:
                break;
            default:
                if (authorizedRole.equals(Role.USER) || authorizedRole.equals(Role.ADMIN)) {
                    throw new AuthorizationException("Rôle insuffisant.");
                }
        }
    }

    private String issueToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
