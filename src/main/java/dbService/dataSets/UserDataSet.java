package dbService.dataSets;

public class UserDataSet {
    private long id;
    private final String login;
    private final String password;

    public UserDataSet(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserDataSet(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserDataSet(long id, String name) {
        this.id = id;
        this.login = name;
        this.password = name;
    }

    public UserDataSet(String login) {
        this.login = login;
        this.password = login;

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }
}
