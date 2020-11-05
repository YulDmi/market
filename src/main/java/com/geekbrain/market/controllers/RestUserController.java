package com.geekbrain.market.controllers;

import com.geekbrain.market.dto.UserDto;
import com.geekbrain.market.entities.DetailsUser;
import com.geekbrain.market.entities.Product;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class RestUserController {
    private final UserService userService;

    @GetMapping
    public UserDto getUserContent(Principal principal) {
        System.out.println("userController " + principal.getName());
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("не найден пользователь с именем :" + principal.getName()));
        return new UserDto(user);
    }

    @PostMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        System.out.println(userDto.getId() + " " +userDto.getFirstname() + " "+ userDto.getLastname() + " " + userDto.getAddress() + " " + userDto.getYear()+ "" + userDto.getSex());
        User user = userService.setDetailsUser(userDto);
        user.setEmail(userDto.getEmail());
        userService.save(user);
        System.out.println(user.getUsername());
        return new UserDto(user);

    }
}
