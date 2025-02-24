package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.business.exceptions.CreditCardException;
import fr.eql.ai116.linus.wattelse.entity.pojo.CreditCard;

import java.util.List;


public interface CreditCardBusiness {
    void addCreditCard(CreditCard creditCardDto) throws CreditCardException;
    List<fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto> fetchUsersCreditCard(Long userId);
}
