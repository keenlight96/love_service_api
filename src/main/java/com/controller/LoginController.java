package com.controller;

import com.model.Account;
import com.model.UserProfile;
import com.model.dto.AccountToken;
import com.service.IAccountService;
import com.service.IRoleService;
import com.service.IUserProfileService;
import com.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<?> getLogin(@RequestBody Account account) {
        Optional<Account> optionalAccount = accountService.login(account.getUsername(), account.getPassword());
        if (!optionalAccount.isPresent()) {
            return new ResponseEntity<>("tài khoản hoặc mật khẩu sai", HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.createToken(authentication);
            UserProfile userProfile = iUserProfileService.getUserProfileByAccount_Id(optionalAccount.get().getId()).orElse(new UserProfile());
            return new ResponseEntity<>(new AccountToken(
                    optionalAccount.get().getId(),
                    optionalAccount.get().getUsername(),
                    token,
                    optionalAccount.get().getNickname(),
                    optionalAccount.get().getAvatar(),
                    userProfile.getBalance(),
                    optionalAccount.get().getRole(),
                    optionalAccount.get().getStatus(),
                    optionalAccount.get().getIsActive()
            ), HttpStatus.OK);
        }
    }
}
