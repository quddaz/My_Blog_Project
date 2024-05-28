package quddaz.myblog.Board.service;

import org.springframework.data.jpa.repository.JpaRepository;
import quddaz.myblog.Board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
