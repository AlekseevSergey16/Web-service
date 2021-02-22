package dbService.dao;

import dbService.dataSets.UserDataSet;
import dbService.executor.Executor;
import java.sql.Connection;
import java.sql.SQLException;


public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public void insertUser(String login, String password) throws SQLException {
        executor.execUpdate("INSERT INTO users (login, password) VALUES ('" + login + "', '" + password + "')");
    }

    public UserDataSet get(String login, String password) throws SQLException {
        return executor.execQuery("select * from users where login=" + login + "'", (result) -> {
            result.next();
            return new UserDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id));");
    }
}
