/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.dto;

/**
 *
 * @author AD
 */
import PassCamp.ass.main.constant.Constant;
import PassCamp.ass.main.constant.ErrorMessage;
import PassCamp.ass.main.constant.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RegisterDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = Constant.REGEX_NAME, message = ErrorMessage.FIRST_NAME)
    private String firstName;

    @NotBlank
    @Pattern(regexp = Constant.REGEX_NAME, message = ErrorMessage.LAST_NAME)
    private String lastName;

    @NotNull
    private Gender gender;

    @Past
    @JsonFormat(pattern = Constant.BIRTHDAY_FORMAT)
    private LocalDate birthday;

    @Email
    private String email;

    @Pattern(regexp = Constant.REGEX_PHONE_NUMBER, message = ErrorMessage.PHONE_NUMBER)
    private String phoneNumber;

    private String address;

}
