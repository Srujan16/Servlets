import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import sun.nio.cs.ext.GB18030;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by srujant on 18/7/16.
 */
public class Users {

    private static final Connection connection= Db.getConnection();
    private static Map<String,Integer> users =new HashMap();


    public static Map<String,Integer> getUsers(){

        int i=0;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select *from users");
            while (resultSet.next()){
                users.put(resultSet.getString("username")+resultSet.getString("password"),i);
                i++;
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
