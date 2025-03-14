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
@Table(name = "Login")
public class Login {
    @Id
    @Column(name = "loginId")
    private String loginId;

    @Column(name = "accountId")
    private String accountId; 

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash; // Store hashed password securely
}


