package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import entities.User;
import paths.PathDocuments;
import utilities.VerifyEmailRegistry;
import utilities.VerifyType;

public class RegisterUser {
    public static User register(Scanner sc, List<User> users) {
        User userLogged = null;
        System.out.printf("Digite seu Nome: %n");
        String name = sc.nextLine();
        System.out.printf("Digite o Email: %n");
        String email = VerifyType.verifyEmail(sc);
        System.out.printf("Digite a Senha: %n");
        String password = sc.nextLine();
        if (!VerifyEmailRegistry.verify(users, email)) {
            System.out.printf("Email ja cadastrado %n");

        } else {
            users.add(new User((users.getLast().getIdUser() + 1), name, email, password, entities.enums.Status.ACTIVE));
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PathDocuments.USER_PATH, true))) {
                bw.write(users.getLast().toString());
            } catch (Exception e) {
                System.out.printf("Erro ao Escrever Documento %n");
            }

            System.out.printf("Usuario Cadastrado com Sucesso %n");
            userLogged = users.getLast();
        }
        return userLogged;
    }
}
