package accounts;

import dbService.dataSets.UserDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserDataSet> loginToProfile;
    private final Map<String, UserDataSet> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserDataSet userDataSet) {
        loginToProfile.put(userDataSet.getLogin(), userDataSet);
    }

    public UserDataSet getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserDataSet userDataSet) {
        sessionIdToProfile.put(sessionId, userDataSet);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
