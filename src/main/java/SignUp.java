import javax.imageio.IIOException;
import javax.management.RuntimeErrorException;
import javax.management.relation.RoleUnresolved;
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
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by srujant on 15/7/16.
 */
public class SignUp extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PreparedStatement preparedStatement;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String query = "insert into users(username,password) values(?,?)";
            Connection connection = Db.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            if (preparedStatement.executeUpdate() != -1) {
                Cookie ck = new Cookie(username, password);
                ck.setMaxAge(15 * 60);
                response.addCookie(ck);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("./search.html");
                requestDispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
