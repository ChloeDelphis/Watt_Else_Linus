package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;

public interface PayementBusiness {
    Tarification getTarification(long tarificationId);
}
