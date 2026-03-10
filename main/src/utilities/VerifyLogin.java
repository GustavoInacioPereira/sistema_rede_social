package utilities;
import java.util.List;
import java.util.Scanner;
import entities.User;

public class VerifyLogin {
    public static User verify(List<User> users, Scanner sc) {
        boolean control = false;
        User userLogged = null;

        while (!control) {
            System.out.printf("Digite o Email: %n");
            String email = VerifyType.verifyEmail(sc).toLowerCase();
            System.out.printf("Digite a Senha: %n");
            String password = sc.nextLine();

            for (User u : users) {
                if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                    System.out.printf("Login Realizado %n");
                    userLogged = u;
                    control = true;
                    break;
                }    
            }
            if(!control) {
                System.out.printf("Email e/ou Senha incorretos %n");
            }
            
        }
       
        return userLogged;
    }
}
