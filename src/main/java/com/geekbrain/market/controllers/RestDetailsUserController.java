package com.geekbrain.market.controllers;

import com.geekbrain.market.dto.DetailsUserDto;
import com.geekbrain.market.entities.DetailsUser;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.services.DetailsUserService;
import com.geekbrain.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user/profiles")
@RequiredArgsConstructor
public class RestDetailsUserController {
    private final DetailsUserService detailsUserService;
    private final UserService userService;

    @GetMapping
    public DetailsUserDto getUserContent(Principal principal) {
        DetailsUser detailsUser = detailsUserService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("не найден пользователь с именем :" + principal.getName()));
        return new DetailsUserDto(detailsUser);
    }

    @PostMapping
    public DetailsUserDto updateUser(@RequestBody DetailsUserDto detailsUserDto) {
        DetailsUser detailsUser = detailsUserService.findByUsername(detailsUserDto.getUsername()).orElseThrow(() -> new ResourceNotFoundException("не найден пользователь с именем :" + detailsUserDto.getUsername()));
        detailsUser.getUser().setEmail(detailsUserDto.getEmail());
        detailsUserService.updateDetailsUser(detailsUserDto);

        return new DetailsUserDto(detailsUser);

    }
}
