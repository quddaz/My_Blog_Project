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
import quddaz.myblog.Comment.domain.Comment;
import quddaz.myblog.Comment.service.CommentService;
import quddaz.myblog.Member.domain.Member;
import quddaz.myblog.Member.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;
  private final CommentService commentService;
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
      return "createboard";
    }
    boardService.createBoard(boardFormDTO);
    return "redirect:/board";
  }
  @GetMapping("/board/{id}")
  public String detailboard(@PathVariable("id") Long id, Model model) {

    Board board = boardService.increaseView(id);
    List<Comment> comments = commentService.getAllCommentsByBoardId(id);
    model.addAttribute("board", board);
    model.addAttribute("comments", comments);

    // detailboard.html 뷰로 이동합니다.
    return "viewboard";
  }
}
