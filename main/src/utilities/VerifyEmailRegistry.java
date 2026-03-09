package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import entities.User;
import paths.PathDocuments;

public class VerifyEmailRegistry {
    public static Boolean verify(List<User> users, String email) {
        boolean result = true;
        try (BufferedReader br = new BufferedReader(new FileReader(PathDocuments.USER_PATH))) {
            String line = br.readLine();

            while (line != null) {
                String[] vectInfos = line.split(";");
                if (vectInfos[2].equalsIgnoreCase(email)) {
                    result = false;
                }
                line = br.readLine();

            }

        } catch (Exception e) {
            System.out.printf("Erro ao Ler Arquivo %n");

        }
        return result;
    }

}
