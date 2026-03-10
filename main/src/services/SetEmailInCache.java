package services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.User;

public class SetEmailInCache {
    public static Set<String> set(List<User> users ) {   
        Set<String> emailsRegistred = new HashSet<>();
        for (User u : users) {
            emailsRegistred.add(u.getEmail());
        }
        return emailsRegistred;
    }
}
