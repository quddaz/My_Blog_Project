package quddaz.myblog.Board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quddaz.myblog.Board.domain.Board;
import quddaz.myblog.Board.domain.BoardFormDTO;
import quddaz.myblog.Member.domain.Member;
import quddaz.myblog.Member.service.MemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
  private final BoardRepository boardRepository;
  private final MemberService memberService;
  public List<Board> getAllBoards() {
    return boardRepository.findAll();
  }
  public Board getBoard(Long id){return  boardRepository.getBoardById(id);}
  @Transactional
  public void createBoard(BoardFormDTO boardFormDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    Member member = memberService.findMemberByUserName(userName).get();

    Board board = boardFormDTO.toEntity();
    board.setMember(member);
    boardRepository.save(board);
  }
  @Transactional
  public Board increaseView(Long id){
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Board not found with id: " + id));
    board.increaseViews();
    boardRepository.save(board);
    return board;
  }

  @Transactional
  public Board updateBoard(Board updatedBoard) {
    // 엔티티를 가져와서 존재하는지 확인
    Board existingBoard = boardRepository.findById(updatedBoard.getId()).orElse(null);
    if (existingBoard == null) {
      // 처리 코드
      throw new RuntimeException("게시물을 찾을 수 없습니다.");
    }

    existingBoard.setTitle(updatedBoard.getTitle());
    existingBoard.setContent(updatedBoard.getContent());
    existingBoard.onUpdate();
    return boardRepository.save(existingBoard);
  }


}
