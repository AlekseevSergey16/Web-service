package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UserDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final DBService dbService;

    public AccountService() {
        dbService = new DBService();
    }

    public void addNewUser(UserDataSet user) {
        try {
            dbService.addUser(user.getLogin(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDataSet getUser(String login, String password) {
        try {
            return dbService.getUser(login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
