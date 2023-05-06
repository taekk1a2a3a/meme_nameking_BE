package com.sparta.meme_nameking.service;


import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.dto.SignupRequestDto;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.exception.CustomException;
import com.sparta.meme_nameking.jwt.JwtUtil;
import com.sparta.meme_nameking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseMsgDto signup(SignupRequestDto signupRequestDto) {
        String nickname = signupRequestDto.getNickname();
        String username = signupRequestDto.getUsername();
        // 비밀번호 암호화 저장
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 닉네임 중복 확인
        Optional<User> foundByNickname = userRepository.findByNickname(nickname);
        if (foundByNickname.isPresent()) {
            throw new CustomException(EXIST_NICKNAME);
        }

        // 유저네임 중복 확인
        Optional<User> foundByUsername = userRepository.findByUsername(username);
        if (foundByUsername.isPresent()) {
            throw new CustomException(EXIST_USERNAME);
        }

        User user = new User(nickname,username, password);
        userRepository.save(user);
        return ResponseMsgDto.setSuccess(StatusEnum.OK.getStatus(), "회원가입 완료", null);
    }



}
