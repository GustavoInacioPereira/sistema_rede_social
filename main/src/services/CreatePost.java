package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import db.dbConnect;
import db.dbException;
import entities.Posts;
import entities.User;

public class CreatePost {
    public static void create(Scanner sc, User userLooged) {
        System.out.printf("Digite o Conteudo do Post %n");
        String content = sc.nextLine();
        LocalDateTime date = LocalDateTime.now();

        Connection conn = dbConnect.getConnection();
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO posts (postContent, datePost, user_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, content);
            pst.setTimestamp(2, Timestamp.valueOf(date));
            pst.setInt(3, userLooged.getIdUser());
            pst.executeUpdate();

            try(ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    int idPost= rs.getInt(1);
                    userLooged.getPosts().add(new Posts(idPost, userLooged.getIdUser(), userLooged.getName(), content, date));
                }
            }
        } catch (Exception e) {
            throw new dbException(e.getMessage());
        }

    }
}
