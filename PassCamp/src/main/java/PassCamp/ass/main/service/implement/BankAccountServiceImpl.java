/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.entity.BankAccount;
import PassCamp.ass.main.repository.BankAccountRepository;
import PassCamp.ass.main.service.BankAccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class BankAccountServiceImpl implements BankAccountService{
    
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllBankAccount(){
        return bankAccountRepository.findAll();
    }
    
    @Override
    public BankAccount getBankAccount(String accountId) {
        return bankAccountRepository.findByAccountId(accountId);
    }
    
}
