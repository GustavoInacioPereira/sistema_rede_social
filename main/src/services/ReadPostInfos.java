package services;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Posts;
import paths.PathDocuments;

public class ReadPostInfos {
    public static List<Posts> read(Integer idUser) {

        List<Posts> posts = new ArrayList<Posts>();
        try (Stream<String> linhasDoArquivo = Files.lines(Paths.get(PathDocuments.POSTS_PATH))) {

            List<Posts> postsArchive = linhasDoArquivo
                    .filter(line -> !line.trim().isEmpty())
                    .map(lines -> lines.split(";"))
                    .filter(vectInfos -> Integer.parseInt(vectInfos[1]) == idUser)
                    .map(vectInfos -> new Posts(Integer.parseInt(vectInfos[0]), Integer.parseInt(vectInfos[1]), vectInfos[2], vectInfos[3], LocalDateTime.parse(vectInfos[4], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))))
                    .collect(Collectors.toList());
            posts.addAll(postsArchive);

        } catch (Exception e) {
            System.out.printf("Erro ao Ler Arquivo %n");

        }
        return posts;
    }
}
