package com.example.demo.legacy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.jdbc.legacy.VanillaJdbc;
import com.example.demo.jdbc.legacy.model.LegacyUser;

public class VanillaJdbcTest {

	@Test
	public void createTableAndDropTable() {
		VanillaJdbc.createTable("users", new String[] { "userid", "password", "age" },
				new String[] { "VARCHAR(50)", "VARCHAR(50)", "int" }, 0);
		VanillaJdbc.dropTable("users");
	}

	@Test
	void crudOperations() {
		VanillaJdbc.createTable("users", new String[] { "userid", "password", "age" },
				new String[] { "VARCHAR(50)", "VARCHAR(50)", "int" }, 0);
		final LegacyUser user = LegacyUser.builder().age(27).userid("fgcornejo").password("test").build();
		VanillaJdbc.insertUserWithManualTx(user);
		VanillaJdbc.updatePassword("fgcornejo", "newPassword");
		final List<LegacyUser> users = VanillaJdbc.getUsers();
		assertTrue(users.size() == 1);
		assertTrue(users.get(0).getPassword().equals("newPassword"));
		VanillaJdbc.deleteUser("fgcornejo");
		VanillaJdbc.dropTable("users");
	}

	@Test
	public void testConnection() throws SQLException {
		final Connection connection = VanillaJdbc.openConnection();
		assertTrue(connection != null && !connection.isClosed());
		VanillaJdbc.closeConnection(connection);
	}

}
