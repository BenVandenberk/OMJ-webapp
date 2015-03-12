package persistency;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

abstract class DBTemplate {

	private String JDBCConnectionString = "jdbc:mysql://localhost/omj2014?user=root&password=java&useUnicode=true&characterEncoding=UTF-8";
	private boolean initialized = false;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	protected abstract <T> String getSchrijfStatement(T object) throws IOException;

	protected abstract String getTableName();

	protected abstract <T> T maakObject(Object[] rij);

	protected String getZoekQuery(HashMap<String, String> zoekParameters, boolean equals) {
		String query = "";
		query += "SELECT * FROM " + getTableName() + " WHERE ";
		String[] keys = zoekParameters.keySet().toArray(new String[zoekParameters.size()]);
		for (int i = 0; i < keys.length; i++) {
			if (equals) {
				query += keys[i] + " LIKE " + String.format("'%s'", zoekParameters.get(keys[i]));
			} else {
				query += keys[i] + " LIKE " + String.format("'%%%s%%'", zoekParameters.get(keys[i]));
			}
			if (i < keys.length - 1) {
				query += " AND ";
			}
		}
		return query;
	}

	protected String getZoekOpIdQuery(int id) {
		return String.format("SELECT * FROM %s WHERE Id = %d;", getTableName(), id);
	}

	protected void schrijfObject(Object object) throws IOException, SQLException {
		initialize();
		setup();
		statement.executeUpdate(getSchrijfStatement(object));
		tearDown();
	}

	protected <T> T zoekObject(int id) throws IOException, SQLException {
		T object;
		initialize();
		setup();

		resultSet = statement.executeQuery(getZoekOpIdQuery(id));
		ResultSetMetaData metaData = resultSet.getMetaData();
		Object[] rij = new Object[metaData.getColumnCount()];
		if (resultSet.first()) {
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				rij[i] = resultSet.getObject(i + 1);
			}
			object = maakObject(rij);
		} else {
			throw new IOException(String.format("Object met id=%s niet gevonden", id));
		}

		tearDown();
		return object;
	}

	protected <T> ArrayList<T> zoekObjecten(HashMap<String, String> zoekParameters, boolean equals) throws IOException,
			SQLException {
		ArrayList<T> objecten = new ArrayList<T>();
		initialize();
		setup();

		resultSet = statement.executeQuery(getZoekQuery(zoekParameters, equals));
		ResultSetMetaData metaData = resultSet.getMetaData();
		Object[] rij = new Object[metaData.getColumnCount()];
		while (resultSet.next()) {
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				rij[i] = resultSet.getObject(i + 1);
			}
			objecten.add((T) maakObject(rij));
		}
		tearDown();
		return objecten;
	}

	protected int getLastID() throws IOException, SQLException {
		initialize();
		setup();
		resultSet = statement.executeQuery(String.format(
				"SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'omj2014' AND TABLE_NAME = '%s';",
				getTableName()));
		resultSet.first();
		int result = resultSet.getInt(1) - 1;
		tearDown();
		return result;
	}

	private void initialize() throws IOException {
		if (initialized) {
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			initialized = true;
		} catch (ClassNotFoundException cnfEx) {
			throw new IOException("jdbc Driver niet gevonden", cnfEx);
		} catch (Exception ex) {
			throw new IOException("Fout bij het registreren van de Driver:\n" + ex.getMessage(), ex);
		}
	}

	private void setup() throws SQLException {
		connection = DriverManager.getConnection(JDBCConnectionString);
		statement = connection.createStatement();
	}

	private void tearDown() throws SQLException {
		connection.close();
		statement.close();
		if (resultSet != null) {
			resultSet.close();
		}
	}

}
