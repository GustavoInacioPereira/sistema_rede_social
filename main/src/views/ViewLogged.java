package views;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entities.Posts;
import entities.User;
import services.CreatePost;
import services.DeleteUserService;
import utilities.VerifyOption;

public class ViewLogged {
    public static void view(Scanner sc, User userLooged, List<User> users, Set<String> emailsRegistred) {
        boolean control = true;
        while (control) {
            List<Posts> globalFeed = new ArrayList<Posts>();
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < users.get(i).getPosts().size(); j++) {
                    globalFeed.add(users.get(i).getPosts().get(j));

                }
            }

            globalFeed.sort(Comparator.comparing(Posts::getDateHour));

            if (globalFeed.isEmpty()) {
                System.out.println("Não Há Posts Realizados");
            } else {
                for (int i = 0; i < globalFeed.size(); i++) {
                    System.out.println("----------------");
                    System.out.println(globalFeed.get(i).getNameUser());
                    System.out.println(
                            globalFeed.get(i).getDateHour().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                    System.out.println(globalFeed.get(i).getPostContent());
                }
            }

            System.out.printf("Digite a Opção abaixo: %n1 - Criar um Post %n2 - Deletar Conta %n3 - Sair");
            int option = VerifyOption.verify(1, 3, sc);

            switch (option) {
                case 1:
                    CreatePost.create(sc, userLooged, globalFeed);
                    break;
                case 2:
                    DeleteUserService.delete(userLooged, users, emailsRegistred);
                    control = false;
                    break;

                case 3:
                    control = false;
                    break;

            }
        }
    }
}
