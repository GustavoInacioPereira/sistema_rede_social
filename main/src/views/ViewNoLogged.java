package views;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import entities.Posts;
import entities.User;

public class ViewNoLogged {
    public static void view(List<User> users) {
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
    }
}
