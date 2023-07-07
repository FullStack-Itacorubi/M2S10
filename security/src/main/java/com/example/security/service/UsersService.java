package com.example.security.service;

import com.example.security.dto.LoginDto;
import com.example.security.entity.UsersEntity;
import com.example.security.repository.UsersRepository;
import com.example.security.security.jwt.JwtProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

    private UsersRepository repository;

    private JwtProvider provider;

    public UsersService(UsersRepository repository, JwtProvider provider) {
        this.repository = repository;
        this.provider = provider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity byUsername = this.repository.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("Não existe esse usuário!");
        }

        return new User(byUsername.getUsername(), byUsername.getPassword(), byUsername.getAuthorities());
    }

    public String login(LoginDto loginDto) {
        Optional<UsersEntity> user =
                this.repository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());

        if (user.isPresent()) {
            return this.provider.generateToken(user.get().getUsername());
        }

        throw new UsernameNotFoundException("Usuário inválido!");
    }
}
