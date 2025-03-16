/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.dto.LoginDto;
import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author AD
 */
public interface LoginService{

    Map<String, Object> getLogin(LoginDto loginDto, 
            HttpServletRequest request,
            HttpServletResponse response
    );
    
    Login getByUserName(String username);

    void logout(
            HttpServletRequest request,
            HttpServletResponse response
    );
    String createLoginAccount(RegisterDto registerDto, String accountId);
}
