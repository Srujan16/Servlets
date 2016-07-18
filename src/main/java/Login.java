import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by srujant on 15/7/16.
 */
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean userLoginStatus=false;
            response.setContentType("text/html");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String query = "select *from users where  username=?;";
            PrintWriter printWriter = response.getWriter();
            Connection connection = Db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(30 * 60);
                    response.addCookie(cookie);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("./search.html");
                    requestDispatcher.forward(request, response);
                    userLoginStatus=true;
                    break;
                }
            }
            if(!userLoginStatus)
            {
                printWriter.println("Invalid user details");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
                requestDispatcher.include(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
