package kz.keste.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool{
    private static ConnectionPool pool = new ConnectionPool();
    private List<Connection> freeConnections;
    private String url;
    private String username;
    private String password;
    private Integer connectionNumber;
    private Semaphore semaphore;
    private static Logger logger = 	Logger.getLogger(ConnectionPool.class.getName());

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setConnectionNumber(Integer connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    private ConnectionPool() {
    }

    public void initConnections(){
        try {

            Class.forName("org.gjt.mm.mysql.Driver");
            semaphore = new Semaphore(connectionNumber, true);
            freeConnections = new ArrayList<Connection>(connectionNumber);
            for (int i = 0; i < connectionNumber; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                freeConnections.add(pooledConnection);
            }
            logger.log(Level.INFO, connectionNumber + " connections created");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return pool;
    }

    public Connection getConnection() {
        try {
            semaphore.acquire();
            return freeConnections.remove(0);

        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }


    private class PooledConnection implements Connection {

        private Connection connection;

        public PooledConnection(Connection c) throws SQLException{
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        public void close() throws SQLException {
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            freeConnections.add(this);
            semaphore.release();
        }

        public void commit() throws SQLException {
            connection.commit();
        }

        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        public Statement createStatement(int resultSetType,int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        public void setSchema(String schema) throws SQLException {

        }

        public String getSchema() throws SQLException {
            return null;
        }

        public void abort(Executor executor) throws SQLException {

        }

        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        public int getNetworkTimeout() throws SQLException {
            return 0;
        }


        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        public void rollback() throws SQLException {
            connection.rollback();
        }

        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }
    }
}

