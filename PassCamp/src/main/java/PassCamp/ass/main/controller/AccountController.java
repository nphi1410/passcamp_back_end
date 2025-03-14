/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.controller;

import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Account;
import PassCamp.ass.main.service.AccountService;
import PassCamp.ass.main.util.Generate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AD
 */
@CrossOrigin(origins = "*")
@Controller
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    private final Map<String, Object> tempData
            = new ConcurrentHashMap<>();

    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(
            @RequestBody RegisterDto registerDto
    ) {
        String verificationCode = Generate.getVerificationCode();

        tempData.put(
                registerDto.getEmail(),
                verificationCode
        );

        tempData.put(
                "registerDto",
                registerDto
        );

        return ResponseEntity.ok(accountService
                .sendEmail(
                        registerDto.getEmail(),
                        verificationCode
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String code
    ) {
        RegisterDto registerDto = (RegisterDto) tempData.get("registerDto");
        String storedCode = (String) tempData.get(registerDto.getEmail());

        if (storedCode == null || !storedCode.equals(code)) {
            return ResponseEntity
                    .badRequest()
                    .body("Email verification failed!");
        }

        String message = "create account success";
        if (!accountService.createAccount(registerDto)) {
            message = "create account failed";
        }
        tempData.remove("registerDto");
        tempData.remove(registerDto.getEmail());

        return ResponseEntity.ok(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccount());
    }
}
