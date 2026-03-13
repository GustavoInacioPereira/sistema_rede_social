package services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import db.dbConnect;
import db.dbException;
import entities.Posts;
import entities.User;
public class ReadPostInfos {
    public static void read(List<User> users) {

        Connection conn = dbConnect.getConnection();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM posts")) {
            while (rs.next()) {
                int idPost = rs.getInt("id");
                String content = rs.getString("postContent");
                LocalDateTime dateTime = rs.getTimestamp("datePost").toLocalDateTime();
                int userPost = rs.getInt("user_id");
                for(User u : users) {
                    if(u.getIdUser() == userPost) {
                        u.getPosts().add(new Posts(idPost, u.getIdUser(), u.getName(), content, dateTime));
                        break;
                    }
                }         
            }
        } catch (Exception e) {
            throw new dbException(e.getMessage());
        }

        
    }
}
