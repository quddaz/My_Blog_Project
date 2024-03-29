package quddaz.myblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quddaz.myblog.domain.Board;
import quddaz.myblog.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;


  public List<Board> getAllBoards() {
    return boardRepository.findAll();
  }

  public Board createBoard(Board board) {
    return boardRepository.save(board);
  }

  public Board updateBoard(Board board) {
    return boardRepository.save(board);
  }

  public void deleteBoard(Long id) {
    boardRepository.deleteById(id);
  }
}
