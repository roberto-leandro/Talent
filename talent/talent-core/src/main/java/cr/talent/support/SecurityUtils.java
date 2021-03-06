package cr.talent.support;

import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Class which provides security utils such as validating a user's password.
 *
 * @author Josué León Sarkis
 */
public class SecurityUtils {

    /**
     * Method which validades a user's password by checking it is at least 8 characters long,
     * it has at least one: number, upper case letter, lower case letter and symbol.
     *
     * @param password The user's password to validate
     */
    public static void validatePassword(String password) {

        boolean passwordTooShort = password.length() < 8;
        if (passwordTooShort)
            throw new IllegalArgumentException("Invalid password, the password should at least have 8 characters.");

        boolean noNumber = true;
        boolean noUpperCaseLetter = true;
        boolean noLowerCaseLetter = true;
        boolean noSymbol = true;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (noNumber)
                noNumber = !(c >= 48 && c <= 57);
            if (noUpperCaseLetter)
                noUpperCaseLetter = !(c >= 65 && c <= 90);
            if (noLowerCaseLetter)
                noLowerCaseLetter = !(c >= 97 && c <= 122);
            if (noSymbol)
                noSymbol = !((c >= 33 && c <= 47) || (c >= 58 && c <= 64));

            if (!(noNumber || noUpperCaseLetter || noLowerCaseLetter || noSymbol))
                break;
        }

        if (noNumber)
            throw new IllegalArgumentException("Invalid password, the password should at least have a number.");
        if (noUpperCaseLetter)
            throw new IllegalArgumentException("Invalid password, the password should at least have an upper case letter.");
        if (noLowerCaseLetter)
            throw new IllegalArgumentException("Invalid password, the password should at least have a lower case letter.");
        if (noSymbol)
            throw new IllegalArgumentException("Invalid password, the password should at least have a symbol.");
    }

    /**
     * Gets the currently logged in User via the SecurityContext which returns a principal object that can be casted
     * to User.
     *
     * @return the User currently logged in or null if there's no currently logged in user
     */
    public static User getLoggedInUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if ((principal instanceof User))
                return (User) principal;
        }

        return null;
    }

    /**
     * Gets the currently logged in Technical Resource by casting the logged in user.
     *
     * @return the Technical Resource logged in or null if there's no currently logged in user
     */
    public static TechnicalResource getLoggedInTechnicalResource() {
        return (TechnicalResource) getLoggedInUser();
    }

}
