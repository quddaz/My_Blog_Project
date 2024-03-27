package quddaz.myblog.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import quddaz.myblog.component.CustomAuthFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final CustomAuthFailureHandler customAuthFailureHandler;

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
    httpSecurity
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/login","/","/join").permitAll() // 해당 위치는 접근 가능
                .anyRequest().authenticated() // 나머지 모든 페이지는 인증 검사
        );
    httpSecurity
        .formLogin(login ->
            login
                .loginPage("/login") // 로그인 페이지 설정
                .defaultSuccessUrl("/main", true)
                .loginProcessingUrl("/login")
                .failureHandler(customAuthFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
        );
    httpSecurity
        .csrf(csrf -> csrf.disable()); // CSRF 사용 안함
    httpSecurity
        .logout(logout -> logout
            .logoutUrl("/logout")  // 로그아웃 URL 지정
            .logoutSuccessUrl("/login")  // 로그아웃 성공 시 이동할 URL 지정
            .invalidateHttpSession(true)  // 세션 무효화
            .deleteCookies("JSESSIONID") // 쿠키 삭제
            .permitAll());
    return httpSecurity.build();
  }
}