/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.service.implement;

import PassCamp.ass.main.entity.BankPayment;
import PassCamp.ass.main.repository.BankPaymentRepository;
import PassCamp.ass.main.service.BankPaymentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AD
 */
@Service
public class BankPaymentServiceImpl implements BankPaymentService{

    @Autowired
    private BankPaymentRepository bankPaymentRepository;
    
    @Override
    public List<BankPayment> getBankPayments() {
        return bankPaymentRepository.findAll();
    }

    @Override
    public BankPayment getBankPaymentHistory(String transferId) {
        return bankPaymentRepository.findByTransferId(transferId);
    }
    
}
