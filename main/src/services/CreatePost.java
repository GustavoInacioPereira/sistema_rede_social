package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import entities.Posts;
import entities.User;
import paths.PathDocuments;
import utilities.VerifyOption;

public class CreatePost {
    public static boolean create(Scanner sc, User userLooged, List<Posts> globalFeed) {
        boolean control = true;
        System.out.printf("Digite a Opção abaixo: %n1 - Criar um Post %n2 - Sair %n");
        int option = VerifyOption.verify(1, 2, sc);

        switch (option) {
            case 1:
                System.out.printf("Digite o Conteudo do Post %n");
                String content = sc.nextLine();

                if (globalFeed.isEmpty()) {
                    userLooged.getPosts().add(new Posts(0, userLooged.getIdUser(),
                            userLooged.getName(), content, LocalDateTime.now()));
                } else {
                    userLooged.getPosts().add(new Posts((globalFeed.getLast().getIdPost() + 1), userLooged.getIdUser(),
                            userLooged.getName(), content, LocalDateTime.now()));
                }

               
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(PathDocuments.POSTS_PATH, StandardCharsets.UTF_8, true))) {
                    bw.newLine();
                    bw.write(userLooged.getPosts().getLast().toString());
                } catch (Exception e) {
                    System.out.printf("Erro ao Escrever Documento %n");
                }

                break;

            case 2:
                control = false;
                break;
            default:
                break;
        }
        return control;

    }
}
