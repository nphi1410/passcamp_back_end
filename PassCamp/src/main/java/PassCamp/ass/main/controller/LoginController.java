/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.dto.LoginDto;
import PassCamp.ass.main.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AD
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Object> submitLogin(
            @RequestBody LoginDto loginDto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Map<String, Object> res = loginService.getLogin(
                loginDto,
                request,
                response
        );

        HttpSession session = request.getSession();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        return ResponseEntity.ok("logged out");
    }

    @GetMapping("/session")
    public ResponseEntity<Map<String, Object>> getSessionStatus(
            HttpServletRequest request, 
            HttpServletResponse response
    ) {
        HttpSession session = request.getSession(false);  // Get existing session, don't create a new one
        Cookie[] cookies = request.getCookies();
        Map<String, Object> res = new HashMap<>();
        Cookie currentCookie = null;

        // Look for the "rememberMe" cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    currentCookie = cookie;
                    break;
                }
            }
        }

        // Check if the "rememberMe" cookie is not found
        if (currentCookie == null) {
            res.put("cookie status", "expired");
        } else {
            res.put("cookie name", "rememberMe");
            res.put("cookie status", "active");

            if (session == null || session.getAttribute("session") == null) {
                session = request.getSession(true);  
                session.setAttribute("session", currentCookie.getValue());
                session.setMaxInactiveInterval(20);  
            }
        }

        // Check session status
        if (session != null && session.getAttribute("session") != null) {
            res.put("session status", "active");
            res.put("session user", session.getAttribute("session"));
            res.put("session time remaining", session.getMaxInactiveInterval() + " seconds");
        } else {
            res.put("session status", "expired");
        }

        return ResponseEntity.ok(res);
    }
}
