package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import db.dbConnect;
import db.dbException;
import entities.User;
import entities.enums.Status;
import utilities.VerifyEmailRegistry;
import utilities.VerifyType;

public class RegisterUser {
    public static User register(Scanner sc, List<User> users, Set<String> emailsRegistred) {
        User userLogged = null;
       
        System.out.printf("Digite seu Nome: %n");
        String name = sc.nextLine();
        System.out.printf("Digite o Email: %n");
        String email = VerifyType.verifyEmail(sc);
        System.out.printf("Digite a Senha: %n");
        String password = sc.nextLine();

        if (!VerifyEmailRegistry.verify(email, emailsRegistred)) {
            System.out.printf("Email ja cadastrado %n");
        } else {
            Connection conn = dbConnect.getConnection();
            try (PreparedStatement pst = conn.prepareStatement("INSERT INTO users (nome, email, senha, statusUser) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {               
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, Status.ACTIVE.name());
                pst.executeUpdate();

                try(ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerate = rs.getInt(1);
                        users.add(new User(idGenerate, name, email, password, entities.enums.Status.ACTIVE));
                    }
                    
                }

            } catch (Exception e) {
                throw new dbException(e.getMessage());
            }

            System.out.printf("Usuario Cadastrado com Sucesso %n");
            userLogged = users.getLast();
        }
        return userLogged;
    }
}
