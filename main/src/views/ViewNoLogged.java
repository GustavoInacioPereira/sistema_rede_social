package views;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entities.Posts;
import entities.User;

public class ViewNoLogged {
    public static void view(List<User> users) {
        List<Posts> globalFeed = users.stream()
            .flatMap(user -> user.getPosts().stream())
            .sorted(Comparator.comparing(Posts::getDateHour))
            .collect(Collectors.toList());
    

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
    }
}
