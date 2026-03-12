package services;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import entities.User;
import paths.PathDocuments;

public class DeleteUserService {

    public static void delete(User userToDelete, List<User> users, Set<String> emailsRegistred) {

        try (Stream<String> lineArchive = Files.lines(Paths.get(PathDocuments.USER_PATH))) {
            List<String> fileContent = lineArchive
                    .filter(line -> !line.trim().isEmpty())
                    .map(lines -> {
                        String[] parts = lines.split(";");
                        if (Integer.parseInt(parts[0]) == userToDelete.getIdUser()) {
                            return parts[0] + ";" + parts[1] + ";" + parts[2] + ";" + parts[3] + ";DISABLE";
                        }
                        return lines;
                    })
                    .collect(Collectors.toList());

            Files.write(Paths.get(PathDocuments.USER_PATH), fileContent);
            emailsRegistred.remove(userToDelete.getEmail());
            users.remove(userToDelete);
            System.out.println("Usuário desativado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo para exclusão.");
        }
        
    }
}