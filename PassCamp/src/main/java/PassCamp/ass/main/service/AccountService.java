/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Account;
import java.util.List;

/**
 *
 * @author AD
 */
public interface AccountService {
    public List<Account> getAccount();
    
    public Account getAccount(String accountId);
    
    public String updateAccount(Account updatedAccount);

    public boolean createAccount(RegisterDto registerDto);

    public String sendEmail(String email, String verificationCode);
}
