package quddaz.myblog.Board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import quddaz.myblog.Board.domain.Board;
import quddaz.myblog.Board.domain.BoardFormDTO;
import quddaz.myblog.Board.service.BoardRepository;
import quddaz.myblog.Board.service.BoardService;

@Controller
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;
  private final BoardRepository boardRepository;
  @GetMapping("/board")
  public String boardMain(Model model){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();
    model.addAttribute("name", name);
    model.addAttribute("boardList", boardService.getAllBoards());
    return "board";
  }
  @GetMapping("/board/create")
  public String createBoard(Model model){
    model.addAttribute("boardFormDTO",new BoardFormDTO());
    return "createboard";
  }
  @PostMapping("/board/create")
  public String createBoard(@Valid @ModelAttribute("boardFormDTO") BoardFormDTO boardFormDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "createboard"; // Return back to the form if there are validation errors
    }
    boardService.createBoard(boardFormDTO);
    return "redirect:/board"; // Redirect to the board list page after successful creation
  }
  @GetMapping("/board/detail/{id}")
  public String detailboard(@PathVariable("id") Long id, Model model) {
    // id를 이용하여 해당 보드를 데이터베이스에서 조회합니다.

    Board board = boardService.increaseView(id);
    // 모델에 보드 정보를 담아서 뷰로 전달합니다.
    model.addAttribute("board", board);

    // detailboard.html 뷰로 이동합니다.
    return "viewboard";
  }
}
