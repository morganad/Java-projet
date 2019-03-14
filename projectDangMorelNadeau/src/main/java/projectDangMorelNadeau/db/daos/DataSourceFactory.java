package projectDangMorelNadeau.db.daos;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {

	private static MysqlDataSource dataSource;

	public static DataSource getDataSource() {
		// TODO à mettre en paramètres ou demander au prof de changer les données
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("java");
			dataSource.setUser("root");
			dataSource.setPassword("");
		}
		return dataSource;
	}
}
