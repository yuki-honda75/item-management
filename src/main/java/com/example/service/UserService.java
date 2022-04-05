package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author hondayuki
 *
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * パスワードをエンコードして登録する
     * 
     * @param user
     */
    public void register(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        userRepository.insert(user);
    }

    /**
     * 名前（メールアドレス）が登録されているかチェック
     * 
     * @param name
     * @return
     */
    public User checkUser(String name) {
        return userRepository.findByName(name);
    }
}
