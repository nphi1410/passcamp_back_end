/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.repository;

import PassCamp.ass.main.entity.BankPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AD
 */
@Repository
public interface BankPaymentRepository extends JpaRepository<BankPayment, String> {
    
    BankPayment findByTransferId(String transferId);
}
