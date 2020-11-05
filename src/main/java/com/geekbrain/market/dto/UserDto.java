package com.geekbrain.market.dto;


import com.geekbrain.market.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private Integer year;
    private String sex;
    private String phone;

    public UserDto(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = user.getDetails().getFirstname();
        this.email = user.getEmail();
        this.lastname = user.getDetails().getLastname();
        this.address = user.getDetails().getAddress();
        this.year = user.getDetails().getYear();
        this.sex = user.getDetails().getSex();
        this.phone = user.getDetails().getPhone();


    }
}


