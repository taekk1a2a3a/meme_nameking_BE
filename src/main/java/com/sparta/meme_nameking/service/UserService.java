package com.sparta.meme_nameking.service;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.dto.SignupRequestDto;
import com.sparta.meme_nameking.dto.LoginRequestDto;

import com.sparta.meme_nameking.exception.CustomException;
import com.sparta.meme_nameking.exception.ErrorCode;
import com.sparta.meme_nameking.jwt.JwtUtil;
import com.sparta.meme_nameking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.sparta.meme_nameking.exception.ErrorCode.EXIST_NICKNAME;
import static com.sparta.meme_nameking.exception.ErrorCode.EXIST_USERNAME;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<ResponseMsgDto> signup(SignupRequestDto signupRequestDto) {
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

        User user = new User(nickname, username, password);
        userRepository.save(user);
        return ResponseEntity.ok(ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "회원가입 완료", null).getBody());
    }

    @Transactional
    public ResponseEntity<ResponseMsgDto> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.generateToken(String.valueOf(user));
        response.setHeader("Authorization", "Bearer " + token);

        return ResponseEntity.ok(ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "로그인 성공", token).getBody());
    }
}