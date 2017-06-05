package kz.keste.database;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static ConnectionPool pool = ConnectionPool.getInstance();
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";
    private static final String MYSQL_DB_URI = "jdbc:mysql://localhost:3306/keste_db";

    private static boolean poolInitialized = false;

    public static synchronized Connection getConnection() throws SQLException {

        if (!poolInitialized){
            pool.setConnectionNumber(20);
            pool.setUrl(MYSQL_DB_URI);
            pool.setUsername(USERNAME);
            pool.setPassword(PASSWORD);

            pool.initConnections();
            poolInitialized = true;

            return pool.getConnection();
        }

        return pool.getConnection();
    }

}
