package quddaz.myblog.Board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quddaz.myblog.Member.domain.Member;
import quddaz.myblog.domain.Recommend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private String title;

  private String content;

  private Long views = 0L;

  private Timestamp updateTimestamp;

  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
  private List<Recommend> recommendList = new ArrayList<>();

  /**
   * 조회수 1증가 로직
   */
  public void increaseViews() {
    this.views += 1;
  }

  @PreUpdate
  public void onUpdate() {
    this.updateTimestamp = new Timestamp(System.currentTimeMillis());
  }

  // 기본 생성자
  public Board() {
    this.updateTimestamp = new Timestamp(System.currentTimeMillis());
    this.views = 0L;
  }

  public Board(String title, String content) {
    this.updateTimestamp = new Timestamp(System.currentTimeMillis());
    this.views = 0L;
    this.recommendList = new ArrayList<>();
    this.title = title;
    this.content = content;
  }
}
