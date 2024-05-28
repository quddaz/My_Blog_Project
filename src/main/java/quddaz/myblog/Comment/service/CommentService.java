package quddaz.myblog.Comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quddaz.myblog.Board.domain.Board;
import quddaz.myblog.Board.service.BoardService;
import quddaz.myblog.Comment.domain.Comment;
import quddaz.myblog.Member.domain.Member;
import quddaz.myblog.Member.service.MemberService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentService {
  private final BoardService boardService;
  private final MemberService memberService;
  private final CommentRepository commentRepository;

  // 게시물에 연결된 모든 댓글 조회
  public List<Comment> getAllCommentsByBoardId(Long boardId) {
    return commentRepository.findAllByBoardId(boardId);
  }

  @Transactional
  public void createComment(Long id, String content) {
    // 해당 id를 가진 게시물 조회
    Board board = boardService.getBoard(id);

    // 현재 인증된 사용자 조회
    Optional<Member> optionalMember = memberService.findMemberByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    if (!optionalMember.isPresent()) {
      throw new RuntimeException("Current user not found");
    }
    Member member = optionalMember.get();

    // 댓글 생성
    Comment comment = new Comment();
    comment.setBoard(board);
    comment.setMember(member);
    comment.setContent(content);

    // 댓글 저장
    commentRepository.save(comment);
  }

}
