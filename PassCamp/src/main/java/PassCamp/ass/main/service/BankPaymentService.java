/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PassCamp.ass.main.service;

import PassCamp.ass.main.entity.BankPayment;
import java.util.List;

/**
 *
 * @author AD
 */
public interface BankPaymentService {

    List<BankPayment> getBankPayments();
    
    BankPayment getBankPaymentHistory(String TransferId);
    
    
}
