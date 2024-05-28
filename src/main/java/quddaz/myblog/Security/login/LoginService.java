package quddaz.myblog.Security.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quddaz.myblog.Member.domain.Member;
import quddaz.myblog.Member.service.MemberService;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<Member> memberUser = memberService.findMemberByUserName(userName);

    if (memberUser.isPresent()) {
      Member member = memberUser.get();
      return User.builder()
          .username(member.getUserName())
          .password(member.getPassword())
          .build();
    }
    return null;
  }
}