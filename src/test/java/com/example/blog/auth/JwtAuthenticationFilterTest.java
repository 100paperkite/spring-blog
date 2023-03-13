package com.example.blog.auth;

import com.example.blog.web.filter.JwtAuthenticationFilter;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtAuthenticationFilterTest {
    private final JwtService jwtService;
    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public JwtAuthenticationFilterTest(final JwtService jwtService) {
        this.jwtService = jwtService;
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService);
    }

    @Test
    public void JWT_인증에_실패하면_로그인_페이지로_리다이렉트한다() throws Exception {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        request.setCookies(new Cookie("jwt", null));

        //when
        jwtAuthenticationFilter.doFilter(request, response, filterChain);
        
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/admin");
    }

    @Test
    public void JWT_인증에_성공하면_필터를_통과한다() throws Exception {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        // 현재 시점으로 새로 토큰을 발급한다.
        request.setCookies(new Cookie("jwt", jwtService.generateToken()));

        //when
        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
