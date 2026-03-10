package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entities.User;
import services.ReadUserInfos;
import services.SetEmailInCache;
import views.ViewInitialHUB;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in, "CP850");
        List<User> users = new ArrayList<User>();
        ReadUserInfos.read(users);
        Set<String> emailsRegistred = SetEmailInCache.set(users);
        ViewInitialHUB.view(sc, users, emailsRegistred);
    }
}
