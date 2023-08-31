package com.controller;

import com.model.Account;
import com.model.UserProfile;
import com.model.dto.AccountToken;
import com.service.IAccountService;
import com.service.IRoleService;
import com.service.IUserProfileService;
import com.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    IAccountService accountService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IUserProfileService iUserProfileService;
    @PostMapping
    public AccountToken getLogin(@RequestBody Account account){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        account = accountService.login(account.getUsername(),account.getPassword()).orElse(account);
        String token = jwtService.createToken(authentication);
        UserProfile userProfile = iUserProfileService.getUserProfileByAccount_Id(account.getId()).orElse(new UserProfile());
        return new AccountToken(account.getId(),account.getUsername(),token, account.getNickname(), account.getAvatar(), userProfile.getBalance(), account.getRole(),account.getStatus());

    }
}
