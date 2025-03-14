/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.dto.LoginDto;
import PassCamp.ass.main.dto.RegisterDto;

/**
 *
 * @author AD
 */
public interface LoginService {

    String getLogin(LoginDto loginDto);

    String createLoginAccount(RegisterDto registerDto, String accountId);
}
