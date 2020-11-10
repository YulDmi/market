package com.geekbrain.market.services;

import com.geekbrain.market.dto.UserDto;
import com.geekbrain.market.entities.DetailsUser;
import com.geekbrain.market.entities.Role;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void setDetailsUser(User user) {
        DetailsUser detailsUser = new DetailsUser();
        detailsUser.setAddress("");
        detailsUser.setLastname("");
        detailsUser.setFirstname("");
        detailsUser.setPhone("");
        detailsUser.setYear(2000);
        detailsUser.setSex("male");
        user.setDetails(detailsUser);
    }

    public User setDetailsUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("не найден пользователь с Id :" + userDto.getId()));
        DetailsUser detailsUser = new DetailsUser();
        detailsUser.setAddress(userDto.getAddress());
        detailsUser.setLastname(userDto.getLastname());
        detailsUser.setFirstname(userDto.getFirstname());
        detailsUser.setPhone(userDto.getPhone());
        detailsUser.setYear(userDto.getYear());
        detailsUser.setSex(userDto.getSex());
        user.setDetails(detailsUser);
        return user;
    }

}