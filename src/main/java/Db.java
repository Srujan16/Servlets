
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by srujant on 15/7/16.
 */
public class Db {

    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","pramati123");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to register driver",e);
        } catch (SQLException e) {
            throw new RuntimeException("Conenction error with db",e);
        }
    }


    public static Connection getConnection(){
        return connection;
    }

}
