/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author AD
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "BankAccount")
public class BankAccount {

    @Id
    @Column(name = "accountId")
    private String accountId;

    @Column(name = "bankAccountId")
    private String bankAccountId;
    
    @Column(name = "bankName")
    private String bankName;
    
    @Column(name = "qrCode")
    private String qrCode;
}
