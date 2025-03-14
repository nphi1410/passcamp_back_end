/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

/**
 *
 * @author AD
 */
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "BankPayment")
public class BankPayment {

    @Id
    @Column(name = "transferId")
    private String transferId;

    @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "bankAccountId")
    private String bankAccountId;

    @Column(name = "bankName")
    private String bankName;
}

