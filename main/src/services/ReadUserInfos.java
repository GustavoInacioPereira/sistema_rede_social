package services;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import entities.User;
import entities.enums.Status;
import paths.PathDocuments;

public class ReadUserInfos {
    public static List<User> read(List<User> users) {
        try (Stream<String> linhasDoArquivo = Files.lines(Paths.get(PathDocuments.USER_PATH))) { 
                List<User> usuariosDoArquivo = linhasDoArquivo
                 .filter(line -> !line.trim().isEmpty())
                 .map(lines -> lines.split(";"))
                 .filter(vectInfos -> Status.valueOf(vectInfos[4]) == Status.ACTIVE)
                 .map(vectInfos -> new User(Integer.parseInt(vectInfos[0]), vectInfos[1], vectInfos[2], vectInfos[3], Status.ACTIVE, ReadPostInfos.read(Integer.parseInt(vectInfos[0]))))
                 .collect(Collectors.toList());
            
            users.addAll(usuariosDoArquivo);    
        } catch (Exception e) {
            System.out.printf("Erro ao Ler Arquivo %n");
            
        }
        return users;
    }

}
