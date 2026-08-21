package com.aven.backend.service;

import com.aven.backend.model.User;
import com.aven.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public final TextEncryptor tokenEncryptor;

    @Transactional(readOnly = true)
    public User getById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public String decryptAccessToken(User user){
        return tokenEncryptor.decrypt(user.getAccessToken());
    }

    private static Long toLong(Object obj){
        if(obj instanceof Number number){
            return number.longValue();
        }
        return Long.parseLong(String.valueOf(obj));
    }
}
