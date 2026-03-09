package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import entities.User;
import paths.PathDocuments;

public class ReadUserInfos {
    public static List<User> read(List<User> users) {
        try (BufferedReader br = new BufferedReader(new FileReader(PathDocuments.USER_PATH))) { 
            String line = br.readLine();
            while (line != null) {
                SetUser.set(line, users);
                line = br.readLine();
            }
            
        } catch (Exception e) {
            System.out.printf("Erro ao Ler Arquivo %n");
            
        }
        return users;
    }

}
