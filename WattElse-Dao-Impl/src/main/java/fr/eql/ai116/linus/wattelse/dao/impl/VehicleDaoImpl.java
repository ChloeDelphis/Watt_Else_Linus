package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.VehicleDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Remote(VehicleDao.class)
@Stateless
public class VehicleDaoImpl implements VehicleDao {

	private static final Logger logger = LogManager.getLogger();
	private final DataSource dataSource = new WattElseDataSource();

	private static final String REQ_FIND_BY_USER =
			"SELECT v.Id_Vehicule, v.Nom, v.Plaque_immatriculation, p.Id_Type_de_prise, p.Libelle, v.Date_ajout, v.Date_retrait " +
					"FROM obj_vehicule v " +
					"JOIN obj_utilisateur u ON v.Id_Utilisateur = u.Id_Utilisateur " +
					"JOIN gamme_type_de_prise p ON v.Id_Type_de_prise = p.Id_Type_de_prise " +
					"WHERE u.Id_Utilisateur = ? AND v.Date_retrait IS NULL " +
					"ORDER BY v.Nom ";

	private static final String REQ_INSERT_VEHICLE = "INSERT INTO obj_vehicule " +
			"(Id_Type_de_prise, Id_Utilisateur, Plaque_immatriculation, Date_ajout, Nom) " +
			"VALUES ( ?, ?, ?, ?, ?)";

	private static final String REQ_UPDATE_VEHICLE = "UPDATE obj_vehicule " +
			"SET Id_Type_de_prise = ? , " +
			"Id_Utilisateur = ? , " +
			"Plaque_immatriculation = ? , " +
			"Date_ajout = ? , " +
			"Nom = ? " +
			"WHERE Id_Vehicule = ? ";

	private static final String REQ_DELETE_BY_ID = "UPDATE obj_vehicule " +
			"SET Date_retrait = ? " +
			"WHERE Id_Vehicule = ? ";

	@Override
	public List<Vehicle> fetchUsersVehicles(long userId) {
		List<Vehicle> vehicles = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_USER);
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Vehicle vehicle = new Vehicle(
						resultSet.getLong("Id_Vehicule"),
						resultSet.getString("Nom"),
						resultSet.getString("Plaque_immatriculation"),
						DateUtils.convert(resultSet.getDate("Date_ajout")),
						DateUtils.convert(resultSet.getDate("Date_retrait"))
				);
				vehicle.setSocket(new Socket(
						resultSet.getLong("Id_Type_de_prise"),
						resultSet.getString("Libelle")
				));

				vehicles.add(vehicle);
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite lors de la consultation des véhicules en base de données", e);
		}
		return vehicles;
	};

	@Override
	public void addModVehicle(Vehicle vehicle){
		boolean addOrMod = vehicle.getVehicleId() == null;
		String REQ_UP_ADD = addOrMod ? REQ_INSERT_VEHICLE : REQ_UPDATE_VEHICLE;
		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);
			try {
				PreparedStatement statement = connection.prepareStatement(REQ_UP_ADD);
				statement.setLong(1, vehicle.getSocket().getSocketId());
				statement.setLong(2, vehicle.getUserId());
				statement.setString(3, vehicle.getLicensePlate());
				statement.setDate(4, Date.valueOf(vehicle.getAdditionDate()));
				statement.setString(5, vehicle.getVehicleName());
				if (!addOrMod){statement.setLong(6, vehicle.getVehicleId());};
				System.out.println(statement);
				statement.executeUpdate();
				connection.commit();
				logger.info("{} a été inséré/modifié en base de données avec l'id {}", vehicle.getVehicleName(), vehicle.getVehicleId());
			} catch (SQLException e) {
				connection.rollback();
				logger.error("Une erreur s'est produite lors de l'insertion de {}", vehicle.getVehicleName(), e);
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
		}
	};

	@Override
	public void suppVehicle(long vehicleId){
		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);
			try {
				PreparedStatement statement = connection.prepareStatement(REQ_DELETE_BY_ID);
				statement.setDate(1, Date.valueOf(LocalDate.now()));
				statement.setLong(2, vehicleId);
				System.out.println(statement);
				statement.executeUpdate();
				connection.commit();
				logger.info("le véhicule avec l'ID {} a été supprimé en base de données", vehicleId);
			} catch (SQLException e) {
				connection.rollback();
				logger.error("Une erreur s'est produite lors de la suppression du véhicule avec l'ID {}", vehicleId, e);
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
		}
	}

	@Override
	public User getVehicleUser(long vehicleId) {
		return null;
	}
}
