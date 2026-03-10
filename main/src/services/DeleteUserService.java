package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import entities.User;
import paths.PathDocuments;

public class DeleteUserService {
    
    public static void delete(User userToDelete, List<User> users, Set<String> emailsRegistred) {
        List<String> fileContent = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(PathDocuments.USER_PATH))) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == userToDelete.getIdUser()) {
                    line = parts[0] + ";" + parts[1] + ";" + parts[2] + ";" + parts[3] + ";DISABLE";
                }
                
                fileContent.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo para exclusão.");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PathDocuments.USER_PATH))) {
            for (int i = 0; i < fileContent.size(); i++) {
                bw.write(fileContent.get(i));
                if (i < fileContent.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao reescrever o arquivo.");
        }
        emailsRegistred.remove(userToDelete.getEmail());
        users.remove(userToDelete);
        System.out.println("Usuário desativado com sucesso!");
    }
}