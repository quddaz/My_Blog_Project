package quddaz.myblog.Comment.domain;

import jakarta.persistence.*;
import lombok.*;
import quddaz.myblog.Board.domain.Board;
import quddaz.myblog.Member.domain.Member;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  private String content;
}