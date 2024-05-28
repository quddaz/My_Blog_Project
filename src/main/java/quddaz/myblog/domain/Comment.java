package quddaz.myblog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quddaz.myblog.Board.domain.Board;
import quddaz.myblog.Member.domain.Member;

@Entity
@Getter
@Setter
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

  public Comment(){};
}
