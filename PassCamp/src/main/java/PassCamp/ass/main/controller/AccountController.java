/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Account;
import PassCamp.ass.main.service.AccountService;
import PassCamp.ass.main.util.Generate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AD
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private final Map<String, RegisterDto> registerDtoMap = new ConcurrentHashMap<>();
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyEmail(@RequestBody RegisterDto registerDto) {
        String verificationCode = Generate.getVerificationCode();
        verificationCodes.put(registerDto.getEmail(), verificationCode);
        registerDtoMap.put(registerDto.getEmail(), registerDto);

        String message = accountService.sendEmail(registerDto.getEmail(), verificationCode); 

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String code = requestBody.get("code");

        RegisterDto registerDto = registerDtoMap.get(email);
        String storedCode = verificationCodes.get(email);

        Map<String, String> response = new HashMap<>();
        if (registerDto == null || storedCode == null || !storedCode.equals(code)) {
            response.put("message", "Email verification failed!");
            return ResponseEntity.badRequest().body(response);
        }

        accountService.createAccount(registerDto);
        registerDtoMap.remove(email);
        verificationCodes.remove(email);

        response.put("message", "Account created successfully!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccount());
    }
}
