package servlets;

import accounts.AccountService;
import dbService.dataSets.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserDataSet profile = accountService.getUserByLogin(login);
        if (profile != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь уже зарегистрирован");
            return;
        }
        UserDataSet newProfile = new UserDataSet(login, password);
        accountService.addNewUser(newProfile);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Registration was successful");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
