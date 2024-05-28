package quddaz.myblog.Comment.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quddaz.myblog.Comment.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByBoardId(Long boardId);
}
