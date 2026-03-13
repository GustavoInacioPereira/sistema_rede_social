package views;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entities.User;
import services.RegisterUser;
import utilities.VerifyLogin;
import utilities.VerifyOption;

public class ViewInitialHUB {
    public static void view(Scanner sc, List<User> users, Set<String> emailsRegistred) {
        boolean control = true;
        while (control) {
            System.out.printf("------Tela Inicial------ %n");
            System.out.printf(
                    "Digite a Opção Abaixo: %n1 - Fazer Login %n2 - Cadastrar Novo Usuario %n3 - Entrar Sem Login %n4 - Sair %n");
            int option = VerifyOption.verify(1, 4, sc);
            switch (option) {
                case 1:
                    User userLogged = VerifyLogin.verify(users, sc);
                    ViewLogged.view(sc, userLogged, users, emailsRegistred);
                    break;
                case 2:
                    userLogged = RegisterUser.register(sc, users, emailsRegistred);
                    if(userLogged != null) {
                        ViewLogged.view(sc, userLogged, users, emailsRegistred);
                    }
                    break;
                case 3:
                    ViewNoLogged.view(users);
                    break;
                case 4: 
                    control = false;
                    break;
                default:
                    break;
            }
        }

    }
}
