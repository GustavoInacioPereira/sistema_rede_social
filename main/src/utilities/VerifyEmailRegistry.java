package utilities;
import java.util.Set;


public class VerifyEmailRegistry {
    public static Boolean verify(String email, Set<String> emailsRegistred) {
        return !emailsRegistred.contains(email);
    }

}
