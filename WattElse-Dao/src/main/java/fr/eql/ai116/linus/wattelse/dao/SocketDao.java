package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import java.util.List;

public interface SocketDao {

    List<Socket> fetchSockets();
}
