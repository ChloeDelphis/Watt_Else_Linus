package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.PayementBusiness;
import fr.eql.ai116.linus.wattelse.dao.TarificationDao;
import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(PayementBusiness.class)
@Stateless
public class PaymentBusinessImpl implements PayementBusiness {

    @EJB
    TarificationDao tarificationDao;

    @Override
    public Tarification getTarification(long tarificationId) {
        Tarification tarification = tarificationDao.getTarification(tarificationId);
        return null;
    }
}
