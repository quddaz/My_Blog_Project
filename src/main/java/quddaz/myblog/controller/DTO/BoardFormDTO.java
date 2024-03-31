package quddaz.myblog.controller.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class BoardFormDTO {
  @NotBlank(message = "제목은 필수 입력 값입니다.")
  private String title;

  @NotEmpty(message = "내용은 필수 입력 값입니다.")
  private String content;

  private String categories;
}
