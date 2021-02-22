package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UserDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService(Connection connection) {
        this.connection = getSQLiteConnection();
    }

    public UserDataSet getUser(String login, String password) throws DBException {
        try {
            return new UsersDAO(connection).get(login, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(String login, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(login, password);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    private static Connection getSQLiteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:profile.db");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
