package quddaz.myblog.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quddaz.myblog.domain.Member;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByUsername(String username);
}
