package utilities;

import java.util.Scanner;

public class VerifyOption {
    public static int verify (int primeiroParametro, int segundoParametro, Scanner sc) {
        int option = VerifyType.verifyInt(sc);
        do {
                if (option < primeiroParametro || option > segundoParametro) {
                    System.out.println("Codigo Invalido ");
                    System.out.println("Digite outro codigo");
                    option = sc.nextInt();
                    sc.nextLine();
                }  
            } while (option < primeiroParametro || option > segundoParametro);
            return option;
    }
}
