package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.User;
import services.ReadUserInfos;
import views.ViewInitialHUB;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in, "CP850");
        List<User> users = new ArrayList<User>();
        ReadUserInfos.read(users);
        ViewInitialHUB.view(sc, users);
        
    }
}
