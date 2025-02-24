package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.business.exceptions.AuthenticationException;
import fr.eql.ai116.linus.wattelse.business.exceptions.AuthorizationException;
import fr.eql.ai116.linus.wattelse.entity.Role;
import fr.eql.ai116.linus.wattelse.entity.dto.AuthenticateOutDto;

public interface SecurityBusiness {

	AuthenticateOutDto authenticate(String email, String password) throws AuthenticationException;
	void authorize(String authorization, Role role) throws AuthorizationException;
}
