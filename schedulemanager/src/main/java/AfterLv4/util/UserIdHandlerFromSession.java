package AfterLv4.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

public class UserIdHandlerFromSession {
    public static UUID getMyIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UUID) session.getAttribute("user");
    }
}
