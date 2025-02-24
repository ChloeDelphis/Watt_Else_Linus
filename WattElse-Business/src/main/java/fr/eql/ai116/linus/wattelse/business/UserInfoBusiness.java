package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.entity.dto.UserOutDto;

public interface UserInfoBusiness {
    UserOutDto getUserInfoById(long id);
}
