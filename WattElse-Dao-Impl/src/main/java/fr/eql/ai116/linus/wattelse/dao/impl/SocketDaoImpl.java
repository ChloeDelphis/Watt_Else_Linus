package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.SocketDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Remote(SocketDao.class)
@Stateless
public class SocketDaoImpl implements SocketDao {

	private static final Logger logger = LogManager.getLogger();

	private static final String REQ_FIND_SOCKETS =
			"SELECT * FROM gamme_type_de_prise ORDER BY gamme_type_de_prise.Libelle ";

	private final DataSource dataSource = new WattElseDataSource();

	@Override
	public List<Socket> fetchSockets() {
		List<Socket> sockets = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(REQ_FIND_SOCKETS);
			while(resultSet.next()) {
				Long socketId = resultSet.getLong("Id_Type_de_prise");
				String socketLabel = resultSet.getString("Libelle");
				sockets.add(new Socket(socketId, socketLabel));
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite lors de la consultation des vehicules en base de données", e);
		}
		return sockets;
	};
}
