/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.constant.IdPrefix;
import PassCamp.ass.main.dto.LoginDto;
import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Login;
import PassCamp.ass.main.repository.LoginRepository;
import PassCamp.ass.main.service.LoginService;
import PassCamp.ass.main.util.Generate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class LoginServiceImpl implements LoginService 
//        ,UserDetailsService 
        {

    @Autowired
    private LoginRepository loginRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Login user = loginRepository.findByUsername(username);
//        if (user != null) {
//            return User.builder()
//                    .username(user.getUsername())
//                    .password(user.getPasswordHash())
//                    .build();
//        }
//
//        throw new UsernameNotFoundException(username);
//    }

    @Override
    public String getLogin(LoginDto loginDto) {
        Login login = loginRepository.findByUsername(loginDto.getUsername());

        if (login == null || !loginDto.getPassword().equals(login.getPasswordHash())) {
            return "Error: Invalid username or password";
        }
//
//        session.setAttribute("user", login.getUsername());
//        session.setMaxInactiveInterval(60 * 60); // 60 minutes

        if (loginDto.isRemember()) {
            // Create a cookie for 7 days
//            ResponseCookie cookie = ResponseCookie.from("SESSION_ID", session.getId())
//                    .httpOnly(true)
//                    .secure(true)
//                    .path("/")
//                    .maxAge(7 * 24 * 60 * 60) // 7 days
//                    .build();
//            response.addHeader("Set-Cookie", cookie.toString());

            return "Login successful! Cookie set for 7 days.";
        }

        return "Login successful! Session active for 60 minutes.";
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
        loginAccount.setPasswordHash(registerDto.getPassword());

        loginRepository.save(loginAccount);
        return "create login account";
    }

}
