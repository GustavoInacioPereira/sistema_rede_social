package services;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import entities.User;

public class SetEmailInCache {
    public static Set<String> set(List<User> users ) {   
        return users.stream()
                    .map(User::getEmail)
                    .collect(Collectors.toSet());
    }
}
