package services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import db.dbConnect;
import db.dbException;
import entities.User;
import entities.enums.Status;

public class ReadUserInfos {
    public static List<User> read(List<User> users) {
        Connection conn = dbConnect.getConnection();
        try (Statement st = conn.createStatement();ResultSet rs = st.executeQuery("SELECT * FROM users WHERE statusUser = 'ACTIVE'")) {            
            while (rs.next()) {      
                    users.add(new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"), Status.valueOf(rs.getString("statusUser"))));                             
            }
        } catch (Exception e) {
            throw new dbException(e.getMessage());
        }
        return users;
    }

}
