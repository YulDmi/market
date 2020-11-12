package com.geekbrain.market.controllers;


import com.geekbrain.market.configs.JwtTokenUtil;
import com.geekbrain.market.configs.jwt.JwtRequest;
import com.geekbrain.market.configs.jwt.JwtResponse;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.exeptions.MarketError;
import com.geekbrain.market.services.DetailsUserService;
import com.geekbrain.market.services.RoleService;
import com.geekbrain.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final DetailsUserService detailsUserService;
    private final RoleService roleService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bc;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/reg")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Пользователь с таким именем уже существует. Войдите или зарегистрируйтесь новым именем"), HttpStatus.UNAUTHORIZED);
        }
        user.setRoles(roleService.getRoleUser());
        user.setPassword(bc.encode(user.getPassword()));

        detailsUserService.setDetailsUser(userService.save(user));
        return ResponseEntity.ok(user);
    }
}
