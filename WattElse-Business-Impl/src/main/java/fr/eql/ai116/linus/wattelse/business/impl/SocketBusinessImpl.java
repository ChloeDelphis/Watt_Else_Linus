package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.SocketBusiness;
import fr.eql.ai116.linus.wattelse.dao.SocketDao;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

import static java.time.LocalDate.now;

@Remote(SocketBusiness.class)
@Stateless
public class SocketBusinessImpl implements SocketBusiness {

    @EJB
    SocketDao socketDao;

    @Override
    public List<Socket> fetchSockets() {
        List<Socket> sockets = socketDao.fetchSockets();
        return sockets;
    }
}
