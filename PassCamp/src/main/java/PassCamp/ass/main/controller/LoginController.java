/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.dto.LoginDto;
import PassCamp.ass.main.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author AD
 */
@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
   
    @PostMapping("/login")
    public ResponseEntity<String> submitLogin(@RequestBody LoginDto loginDto) {
        String response = loginService.getLogin(loginDto);
        return ResponseEntity.ok(response);
    }
}
