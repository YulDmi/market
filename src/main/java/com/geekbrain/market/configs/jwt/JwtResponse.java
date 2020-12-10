package com.geekbrain.market.configs.jwt;


import com.geekbrain.market.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;

}
