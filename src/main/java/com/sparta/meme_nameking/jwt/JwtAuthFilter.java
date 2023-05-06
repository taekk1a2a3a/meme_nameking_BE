package com.sparta.meme_nameking.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //JWT 토큰 요청 헤더에서 가져오기
        String token = jwtUtil.getTokenFromRequest(request);
        //조건문으로 토큰이 존재하고 유효한 경우
        if (token != null && jwtUtil.validateToken(token)) {
            //토큰에서 사용자 이름 추출
            String username = jwtUtil.getUsernameFromToken(token);
            // UserDeatilsService 사용하여 UserDetails 객체 불러옴
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //인증 객체 생성후 SecurityContextHolder에 설정
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
