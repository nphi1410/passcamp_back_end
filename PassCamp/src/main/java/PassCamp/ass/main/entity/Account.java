/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.entity;

import PassCamp.ass.main.constant.Constant;
import PassCamp.ass.main.constant.ErrorMessage;
import PassCamp.ass.main.constant.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author AD
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "accountId")
    private String accountId;

    @Column(name = "accountName")
    private String accountName;

    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonFormat(pattern = Constant.BIRTHDAY_FORMAT)
    private LocalDate birthday;

    @Email
    private String email;

    @Column(name = "phoneNumber")
    @Pattern(regexp = Constant.REGEX_PHONE_NUMBER, message = ErrorMessage.PHONE_NUMBER)
    private String phoneNumber;

    private String address;

    private int role;

    @Column(name = "isEnable")
    private int isEnable;
}
