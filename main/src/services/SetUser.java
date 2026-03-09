package services;


import java.util.List;

import entities.User;
import entities.enums.Status;

public class SetUser {
    public static void set(String line, List<User> users) {
        String[] vectInfos = line.split(";");
        Status statusUser = Status.valueOf(vectInfos[4]);
                if(statusUser.equals(Status.ACTIVE)) {
                     users.add(new User(Integer.parseInt(vectInfos[0]), vectInfos[1], vectInfos[2], vectInfos[3], statusUser, ReadPostInfos.read(Integer.parseInt(vectInfos[0]))));
                } 
    }
}
