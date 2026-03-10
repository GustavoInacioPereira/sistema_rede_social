package services;

import java.io.BufferedReader;
import java.io.FileReader;
import paths.PathDocuments;

public class GenerateID {
    
    public static int getNextUserId() {
        int maxId = -1;
        
        try (BufferedReader br = new BufferedReader(new FileReader(PathDocuments.USER_PATH))) {
            String line = br.readLine();
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    
                    int currentId = Integer.parseInt(line.split(";")[0]);
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                }
                line = br.readLine();
            }
        } catch (Exception e) {

        }
        return maxId + 1;
    }
}