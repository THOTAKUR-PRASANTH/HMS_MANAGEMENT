package Finial.HMS.SECURITY_CONFIGURATION;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
@Component
public class AdminDetails {

    public static String getAuthenticatedUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();
            } else if (principal instanceof String) {
                return (String) principal; // Assuming the principal is the username
            } else {
                return principal.toString(); // Handle other cases as needed
            }
        }
        return null;
    }

    // You might not need this method if the username is simply refreshed from the context.
    // public static void refreshAuthenticatedUser() {
    //     // Refresh logic here if applicable
    // }
}
