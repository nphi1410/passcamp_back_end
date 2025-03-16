/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.constant.IdPrefix;
import PassCamp.ass.main.constant.IsEnable;
import PassCamp.ass.main.constant.Role;
import PassCamp.ass.main.dto.RegisterDto;
import PassCamp.ass.main.entity.Account;
import PassCamp.ass.main.entity.Cart;
import PassCamp.ass.main.entity.EmailDetails;
import PassCamp.ass.main.repository.AccountRepository;
import PassCamp.ass.main.repository.CartRepository;
import PassCamp.ass.main.service.AccountService;
import PassCamp.ass.main.service.EmailService;
import PassCamp.ass.main.service.LoginService;
import PassCamp.ass.main.util.Generate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CartRepository cartRepository;
    @Lazy
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmailService emailService;

    @Override
    public boolean createAccount(RegisterDto registerDto) {
        Account newAccount = new Account();

        String accountId = Generate.getId(
                IdPrefix.ACCOUNT,
                accountRepository.findLastAccountId()
        );
        newAccount.setAccountId(accountId);

        newAccount.setAccountName(registerDto.getFirstName() + " " + registerDto.getLastName());
        newAccount.setUsername(registerDto.getUsername());
        newAccount.setEmail(registerDto.getEmail());
        newAccount.setPhoneNumber(registerDto.getPhoneNumber());
        newAccount.setAddress(registerDto.getAddress());
        newAccount.setBirthday(registerDto.getBirthday());
        newAccount.setGender(registerDto.getGender());
        newAccount.setIsEnable(IsEnable.TRUE);
        newAccount.setRole(Role.USER);
        
        Cart cart = new Cart();
        cart.setCartId(IdPrefix.CART+cartRepository.findLastCartId());
        cart.setAccountId(accountId);
        cart.setItemAmount(0);
        cart.setTotalPrice(0);
        
        accountRepository.save(newAccount);
        cartRepository.save(cart);
        loginService.createLoginAccount(registerDto, accountId);
        return true;
    }

    @Override
    public String sendEmail(String email, String verificationCode) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject("PassCamp: Verification Code");
        emailDetails.setMsgBody("Your verification code is: " + verificationCode);

        return emailService.sendSimpleMail(emailDetails);
    }

    @Override
    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(String accountId) {
        return accountRepository.findByAccountId(accountId);
    }

    @Override
    public String updateAccount(Account updatedAccount) {
        accountRepository.save(updatedAccount);
        return "updated";
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

}
