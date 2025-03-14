/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AD
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a.accountId FROM Account a ORDER BY a.accountId DESC LIMIT 1")
    String findLastAccountId();
    
    Account findByAccountId(String accountId);
    
}
