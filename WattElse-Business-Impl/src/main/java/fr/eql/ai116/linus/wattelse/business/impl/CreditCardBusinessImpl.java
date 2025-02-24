package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.CreditCardBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.CreditCardException;
import fr.eql.ai116.linus.wattelse.business.impl.utils.CreditCardUtils;
import fr.eql.ai116.linus.wattelse.dao.CreditCardDao;
import fr.eql.ai116.linus.wattelse.dao.exceptions.CreditCardAlreadyExistException;
import fr.eql.ai116.linus.wattelse.entity.pojo.CreditCard;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Remote(CreditCardBusiness.class)
@Stateless
public class CreditCardBusinessImpl implements CreditCardBusiness {

    @EJB
    private CreditCardDao creditCardDao;

    @Override
    public void addCreditCard(CreditCard creditCardDto) throws CreditCardException {
        creditCardDto.setRegisterDate(LocalDate.now());
        try {
            creditCardDao.addCreditCard(creditCardDto);
        } catch (CreditCardAlreadyExistException e) {
            throw new CreditCardException(e.getMessage());
        }
    }

    @Override
    public List<fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto> fetchUsersCreditCard(Long userId) {
        List<CreditCard> creditCardDtos = creditCardDao.fetchUsersCreditCard(userId);
        List<fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto> creditCardsDtoDto = new ArrayList<>();
        for (CreditCard creditCardDto : creditCardDtos) {
            // Masking confidential informations
            String maskedCardNumber = CreditCardUtils.hideCreditCard(creditCardDto.getCardNumber());
            String maskedCcs = CreditCardUtils.hideCss(creditCardDto.getCcs());
            fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto dto = new fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto(
                    creditCardDto.getIdCreditCard(),
                    maskedCardNumber,
                    creditCardDto.getExpiryDate(),
                    maskedCcs
            );
            creditCardsDtoDto.add(dto);
        }
        return creditCardsDtoDto;
    }
}
