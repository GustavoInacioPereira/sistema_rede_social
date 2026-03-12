package services;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import paths.PathDocuments;

public class GenerateID {
    
    public static int getNextUserId() {

        
        try (Stream<String> linhasDoArquivo = Files.lines(Paths.get(PathDocuments.USER_PATH))) {

            int maxId = linhasDoArquivo
                .filter(line -> !line.trim().isEmpty())
                .mapToInt(line -> Integer.parseInt(line.split(";")[0]))
                .max()
                .orElse(0);
                return maxId + 1;
        } catch (Exception e) {
                System.out.println("Erro ao ler o arquivo para gerar ID.");
                return 1;
        }
        
    }
}