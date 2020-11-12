package com.geekbrain.market.services;

import com.geekbrain.market.dto.DetailsUserDto;
import com.geekbrain.market.entities.DetailsUser;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.repositories.DetailsUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DetailsUserService  {
    private DetailsUserRepository detailsUserRepository;

//    public DetailsUser save(DetailsUser user) {
//        return detailsUserRepository.save(user);
//    }

    public DetailsUser save(DetailsUser detailsUser){
        detailsUserRepository.save(detailsUser);
        return detailsUser;
    }

    public Optional<DetailsUser> findByUsername(String username) {
        return detailsUserRepository.findByUsername(username);
    }

    public void setDetailsUser(User user) {
        DetailsUser detailsUser = new DetailsUser();
        detailsUser.setAddress("");
        detailsUser.setLastname("");
        detailsUser.setFirstname("");
        detailsUser.setPhone("");
        detailsUser.setYear(2000);
        detailsUser.setSex("male");
        detailsUser.setUser(user);
       save(detailsUser);
    }

    public DetailsUser updateDetailsUser(DetailsUserDto detailsUserDto){

        DetailsUser detailsUser = findByUsername(detailsUserDto.getUsername()).orElseThrow(() -> new ResourceNotFoundException("not exist"));
        detailsUser.setAddress(detailsUserDto.getAddress());
        detailsUser.setLastname(detailsUserDto.getLastname());
        detailsUser.setFirstname(detailsUserDto.getFirstname());
        detailsUser.setPhone(detailsUserDto.getPhone());
        detailsUser.setYear(detailsUserDto.getYear());
        detailsUser.setSex(detailsUserDto.getSex());
      save(detailsUser);
        return detailsUser;
    }

}