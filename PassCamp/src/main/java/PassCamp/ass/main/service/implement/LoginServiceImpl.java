/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.constant.IdPrefix;
import PassCamp.ass.main.dto.LoginDto;
import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Account;
import PassCamp.ass.main.entity.Login;
import PassCamp.ass.main.repository.LoginRepository;
import PassCamp.ass.main.service.AccountService;
import PassCamp.ass.main.service.LoginService;
import PassCamp.ass.main.util.Generate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public Map<String, Object> getLogin(
            LoginDto loginDto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Login login = loginRepository.findByUsername(loginDto.getUsername());

        Map<String, Object> responseBody = new HashMap<>();
        if (login == null
                || !bCryptPasswordEncoder.matches(
                        loginDto.getPassword(),
                        login.getPasswordHash()
                )) {
            String message = "Error: Invalid username or password";
            responseBody.put("message", message);
            responseBody.put("status", "failed");
            return responseBody;
        }

        Account loginAccount = accountService.getAccount(
                login.getAccountId()
        );

        HttpSession session = request.getSession();
        session.setAttribute("session", loginAccount.getUsername());
        session.setMaxInactiveInterval(60*60);

        if (loginDto.isRemember()) {
            Cookie cookie = new Cookie("rememberMe", loginAccount.getUsername());
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            response.addCookie(cookie);
        }

        responseBody.put("Status", "success");
        responseBody.put("is remem", loginDto);
        responseBody.put("user", loginAccount);

        return responseBody;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("rememberMe", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public String createLoginAccount(RegisterDto registerDto, String accountId) {
        Login loginAccount = new Login();
        String loginId = Generate.getId(
                IdPrefix.LOGIN,
                loginRepository.findLastLoginId()
        );
        loginAccount.setLoginId(loginId);
        loginAccount.setAccountId(accountId);
        loginAccount.setUsername(registerDto.getUsername());
        loginAccount.setPasswordHash(
                bCryptPasswordEncoder.encode(registerDto.getPassword()) // ✅ Password properly encrypted
        );

        loginRepository.save(loginAccount);
        return "Login account created successfully";
    }

    @Override
    public Login getByUserName(String username) {
        return loginRepository.findByUsername(username);
    }

}
