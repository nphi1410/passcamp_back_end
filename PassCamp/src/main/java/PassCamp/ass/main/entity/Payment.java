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
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Payment")
public class Payment {
    
    @Id
    @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "method")
    private String method;

    @Column(name = "accountId")
    private String accountId;

    @Column(name = "moneyAmount")
    private double moneyAmount;

    @Column(name = "payTime")
    private LocalDateTime payTime;
}


