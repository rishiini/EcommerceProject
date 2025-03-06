package com.example.demo.Service;

import com.example.demo.Model.Token;
import com.example.demo.Model.User;
import com.example.demo.Repository.TokenRepository;
import com.example.demo.Repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }


    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password,user.getHashedPassword())){

            return null;
        }

        Token token = createToken(user);
        return tokenRepository.save(token);
    }

    @Override
    public User signUp(String name, String email, String password){
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    private Token createToken(User user){
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,30);
        Date date = calendar.getTime();

        token.setExpiry(date);
        token.setDeleted(false);

        return token;

    }
    @Override
    public User validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedAndExpiryGreaterThan(token, false, new Date());
        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Token not exist");
        }
        return tokenOptional.get().getUser();
    }


    @Override
    public void logout(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(token, false);

        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Token not exist");
        }

        Token token_ = tokenOptional.get();
        token_.setDeleted(true);
        tokenRepository.save(token_);
    }
}
