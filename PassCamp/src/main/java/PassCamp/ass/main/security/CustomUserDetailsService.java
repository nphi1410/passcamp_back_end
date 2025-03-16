/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.security;

import PassCamp.ass.main.entity.Account;
import PassCamp.ass.main.service.AccountService;
import PassCamp.ass.main.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService userService;
    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account user = userService.getAccount(username);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(
                        user.getRole() == 2
                        ? "ROLE_ADMIN" : "ROLE_USER"
                )
        );
        return mapUserToCustomUserDetails(user, authorities);
    }

    private CustomUserDetails mapUserToCustomUserDetails(Account user, List<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getAccountId());
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setPassword(loginService
                .getByUserName(user
                        .getUsername()
                )
                .getPasswordHash()
        );
        customUserDetails.setName(user.getAccountName());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}
