package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.pojo.Facturation;

public interface FacturationDao {
    Facturation getLastFacturation(long userId);
    Facturation insertNewFacturation(Facturation facturation);
    void updateFacturation(Facturation facturation);
}
