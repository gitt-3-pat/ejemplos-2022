package com.example.demo.jdbc.legacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.example.demo.jdbc.legacy.model.LegacyUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VanillaJdbc {

	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null & !connection.isClosed()) {
				log.debug("Conexión cerrada");
				connection.close();
			}
		} catch (final SQLException e) {
			log.error("No se pudo cerrar la conexión");
		}
	}

	public static void createTable(String table, String[] columns, String[] dataType, int primaryKeyIndex) {
		Assert.isTrue(columns.length == dataType.length, "El número de columnas y tipo de datos no coinciden");
		final StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + table + " (");
		for (int i = 0; i < columns.length; i++) {
			sql.append(columns[i] + " " + dataType[i]);
			if (i == 0) {
				sql.append(" PRIMARY KEY");
			}
			if (i < columns.length - 1) {
				sql.append(", ");
			}
		}
		sql.append(");");
		log.info("Create table query is: {}", sql.toString());
		Statement statement = null;
		final Connection connection = openConnection();
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (final SQLException e) {
			log.error("Could not create Statement", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}

	}

	public static void deleteUser(String userid) {
		PreparedStatement statement = null;
		final Connection connection = openConnection();
		try {
			statement = connection.prepareStatement("DELETE FROM users WHERE userid = ? ;");
			statement.setString(1, userid);
			statement.executeUpdate();
		} catch (final SQLException e) {
			log.error("Could not update password", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}
	}

	public static void dropTable(String table) {
		Statement statement = null;
		final Connection connection = openConnection();
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DROP TABLE " + table + ";");
			log.info("Dropping table {}", table);
		} catch (final SQLException e) {
			log.error("Could not create Statement", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}
	}

	public static List<LegacyUser> getUsers() {
		PreparedStatement statement = null;
		ResultSet result = null;
		final Connection connection = openConnection();
		final List<LegacyUser> users = new ArrayList<>();
		try {
			statement = connection.prepareStatement("SELECT userid, password, age FROM users;");
			result = statement.executeQuery();
			while (result.next()) {
				users.add(LegacyUser.builder().userid(result.getString(1)).password(result.getString(2))
						.age(result.getInt(3)).build());
			}
		} catch (final SQLException e) {
			log.error("Could not update password", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (result != null && !result.isClosed()) {
					result.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}
		return users;
	}

	public static void insertUser(LegacyUser user) {
		PreparedStatement statement = null;
		final Connection connection = openConnection();
		try {
			statement = connection.prepareStatement("INSERT INTO users (userid, password, age) VALUES(?,?,?);");
			statement.setString(1, user.getUserid());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getAge());
			statement.executeUpdate();
		} catch (final SQLException e) {
			log.error("Could not create user", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}
	}

	public static void insertUserWithManualTx(LegacyUser user) {
		PreparedStatement statement = null;
		final Connection connection = openConnection();
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("INSERT INTO users (userid, password, age) VALUES(?,?,?);");
			statement.setString(1, user.getUserid());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getAge());
			statement.executeUpdate();
			connection.commit();
		} catch (final SQLException e) {
			log.error("Could not create user", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static Connection openConnection() {
		Connection connection = null;
		try {
			// Ensure class is loaded in the JVM
			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/legacy", USERNAME, PASSWORD);
			if (!connection.isClosed()) {
				log.debug("Conexión abierta");
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			log.error("Class loading exception", e);
		} catch (final SQLException e) {
			log.error("SQL Exception", e);
		}
		return connection;
	}

	public static void updatePassword(String userid, String newPassword) {
		PreparedStatement statement = null;
		final Connection connection = openConnection();
		try {
			statement = connection.prepareStatement("UPDATE users SET password = ? WHERE userid = ? ;");
			statement.setString(1, newPassword);
			statement.setString(2, userid);
			statement.executeUpdate();
		} catch (final SQLException e) {
			log.error("Could not update password", e);
		} finally {
			try {
				if (statement != null & !statement.isClosed()) {
					statement.close();
				}
				if (connection != null) {
					closeConnection(connection);
				}
			} catch (final SQLException e) {
				log.error("Could not close statement", e);
			}
		}
	}

}
