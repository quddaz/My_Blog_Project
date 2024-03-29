package quddaz.myblog.controller.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import quddaz.myblog.domain.Member;

@Getter
@Setter
public class JoinDTO {
  @NotBlank(message = "이름은 필수 입력 값입니다.")
  @Length(min = 3,max = 16,message = "아이디를 8자 이상, 16자 이하로 입력해주세요")
  private String username;

  @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
  @Length(min = 8,max = 16,message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
  private String password;

  @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
  @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
  private String tell;

}