package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import entities.Posts;
import paths.PathDocuments;

public class ReadPostInfos {
    public static List<Posts> read(Integer idUser) {
        
        List<Posts> posts = new ArrayList<Posts>();
        try (BufferedReader br = new BufferedReader(new FileReader(PathDocuments.POSTS_PATH))) { 
            String line = br.readLine();
            while (line != null) {
                String[] vectInfos = line.split(";");
                if(Integer.parseInt(vectInfos[1]) == idUser) {
                    posts.add(new Posts(Integer.parseInt(vectInfos[0]), Integer.parseInt(vectInfos[1]), vectInfos[2], vectInfos[3], LocalDateTime.parse(vectInfos[4], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));      
                }
                
                line = br.readLine();
            }
            
        } catch (Exception e) {
            System.out.printf("Erro ao Ler Arquivo %n");
            
        }
        return posts;
    }
}
