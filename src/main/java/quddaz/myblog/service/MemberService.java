package quddaz.myblog.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quddaz.myblog.controller.DTO.JoinDTO;
import quddaz.myblog.domain.Member;
import quddaz.myblog.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true) // 모든 메서드에 읽기 전용 트랜잭션 적용
public class MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional // 쓰기 작업에 대한 트랜잭션 적용
  public void join(JoinDTO joinDTO){
    Member member = new Member();
    member.setUsername(joinDTO.getUsername());
    member.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
    member.setTell(joinDTO.getTell());

    memberRepository.save(member);
  }

  public boolean validateDuplicateMember(String username) {
    if (username == null) {
      return false;
    }
    return !memberRepository.findByUsername(username).isEmpty();
  }
}
