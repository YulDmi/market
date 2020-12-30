package com.geekbrain.market.dto;


import com.geekbrain.market.entities.DetailsUser;
import com.geekbrain.market.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailsUserDto {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private Integer year;
    private String sex;
    private String phone;

    public DetailsUserDto(DetailsUser user) {

        this.id = user.getId();
        this.username =user.getUser().getUsername();
        this.firstname = user.getFirstname();
        this.email = user.getUser().getEmail();
        this.lastname = user.getLastname();
        this.address = user.getAddress();
        this.year = user.getYear();
        this.sex = user.getSex();
        this.phone = user.getPhone();
    }






}


