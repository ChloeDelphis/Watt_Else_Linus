package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;

public interface TarificationDao {
    Tarification getTarification(long tarificationId);
    Tarification insertTarification(Tarification tarification);
    void updateTarification(Tarification tarification);
}
