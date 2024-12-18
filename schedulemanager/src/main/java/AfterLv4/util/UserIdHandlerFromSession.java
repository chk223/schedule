package AfterLv4.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

public class UserIdHandlerFromSession {
    /**
     * 세션에서 로그인 한 유저의 id 반환
     * @param request 세션을 이용하기 위한 HttpServletRequest
     * @return 해당 유저의 id
     */
    public static UUID getMyIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UUID) session.getAttribute("user");
    }
}
