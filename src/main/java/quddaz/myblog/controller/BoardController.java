package quddaz.myblog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import quddaz.myblog.controller.DTO.BoardFormDTO;
import quddaz.myblog.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardService boardService;

  @GetMapping("/")
  public String boardMain(Model model){
    model.addAttribute("boardList", boardService.getAllBoards());
    return "board";
  }
  @GetMapping("/create")
  public String createBoard(Model model){
    model.addAttribute("boardFormDTO",new BoardFormDTO());
    return "createboard";
  }
  @PostMapping("/create")
  public String createBoard(@Valid @ModelAttribute("boardFormDTO") BoardFormDTO boardFormDTO, BindingResult bindingResult){
    return "redirect:board";
  }
}
