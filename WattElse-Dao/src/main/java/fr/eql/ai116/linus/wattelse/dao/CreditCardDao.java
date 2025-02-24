package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.pojo.CreditCard;

import java.util.List;

public interface CreditCardDao {

    List<CreditCard> fetchUsersCreditCard(Long userId);

    void addCreditCard(CreditCard CreditCardDto);

    void modCreditCard(CreditCard CreditCardDto);

    void suppCreditCard(CreditCard CreditCardDto);

    /// User getCreditCardUser(CreditCard CreditCard);
}

