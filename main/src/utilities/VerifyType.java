package utilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VerifyType {
    public static int verifyInt (Scanner sc) {       
        while (true) {
            try {
                int num = sc.nextInt();
                sc.nextLine();
                return num;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Valor Inválido");
            }
        }
    }
   
        

    public static double verifyDouble (Scanner sc) {       
        while (true) {
            try {
                double num = sc.nextDouble();
                sc.nextLine();
                return num;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Valor Inválido");
            }
        }
    }
    
    public static LocalDateTime verifyDateTime (Scanner sc) {       
        while (true) {
            try {
                LocalDateTime date = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));;
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Valor Inválido");
            }
        }
    }
        
    public static String verifyEmail (Scanner sc) {
        Pattern pattern = Pattern.compile("^[A-Z0-9-._%+-]+@[A-Z0-9.-]+.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        boolean controle = false;
        String email = "";
        do {
            email = sc.nextLine();
            Matcher matcher = pattern.matcher(email);
            boolean verificador = matcher.matches();
            if (verificador == true) {
                controle = true;
            } else {
                System.err.printf("Email Inválido %nDigite Novamente: %n");
            }
        } while (controle != true);
        return email;
        
    
    }


}
