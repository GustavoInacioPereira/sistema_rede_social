package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Posts {
    private Integer idUser, idPost;
    private String postContent;
    private String nameUser;
    private LocalDateTime dateHour;

    public Posts(Integer idPost, Integer idUser, String nameUser, String postContent, LocalDateTime dateHour) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.postContent = postContent;
        this.dateHour = dateHour;
    }


    public Integer getIdPost() {
        return idPost;
    }
    public String getNameUser() {
        return nameUser;
    }
    public String getPostContent() {
        return postContent;
    }
    public LocalDateTime getDateHour() {
        return dateHour;
    }
    public Integer getIdUser() {
        return idUser;
    }

    @Override
    public String toString() {
        return idPost + ";" + idUser + ";" + nameUser + ";" + postContent + ";" + dateHour.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
    }
    
}
