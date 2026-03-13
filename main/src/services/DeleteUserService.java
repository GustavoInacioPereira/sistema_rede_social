package services;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.Set;


import db.dbConnect;
import db.dbException;
import entities.User;
import entities.enums.Status;

public class DeleteUserService {

    public static void delete(User userToDelete, List<User> users, Set<String> emailsRegistred) {
        Connection conn = dbConnect.getConnection();
        try (PreparedStatement pst = conn.prepareStatement("UPDATE users SET statusUser = ? WHERE id = ?")) {
            pst.setString(1, Status.DISABLE.name());
            pst.setInt(2, userToDelete.getIdUser());
            pst.executeUpdate();
            
            emailsRegistred.remove(userToDelete.getEmail());
            users.remove(userToDelete);
            System.out.println("Usuário desativado com sucesso!");
        } catch (Exception e) {
            throw new dbException(e.getMessage());
        }

        
    }
}