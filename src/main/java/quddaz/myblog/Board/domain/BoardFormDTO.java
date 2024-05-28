package quddaz.myblog.Board.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import quddaz.myblog.Member.domain.Member;

@Getter
@Data
public class BoardFormDTO {
  private String title;

  private String content;


  public Board toEntity() {
    Board board = new Board();
    board.setTitle(this.title);
    board.setContent(this.content);
    return board;
  }
}