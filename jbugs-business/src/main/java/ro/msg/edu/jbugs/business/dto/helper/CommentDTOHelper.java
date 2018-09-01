package ro.msg.edu.jbugs.business.dto.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.persistence.entity.Comment;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CommentDTOHelper {


    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private UserDTOHelper userDTOHelper;

    @EJB
    private BugDTOHelper bugDTOHelper;

    private Logger logger = LogManager.getLogger(CommentDTOHelper.class);

    public CommentDTO fromEntity(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getIdComment());
        commentDTO.setDate(comment.getDate());
        commentDTO.setText(comment.getText());
        commentDTO.setUser(comment.getUser().getUsername());
        commentDTO.setBugDTO(bugDTOHelper.fromEntity(comment.getBug()));
        return commentDTO;
    }

    public Comment toEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setIdComment(commentDTO.getId());
        comment.setDate(commentDTO.getDate());
        comment.setText(commentDTO.getText());
        comment.setBug(bugDTOHelper.toEntity(commentDTO.getBugDTO()));

        try {
            UserDTO userDTO = userBusinessService.getUserByUsername(commentDTO.getUser());
            comment.setUser(userDTOHelper.toEntity(userDTO));
        } catch (BusinessException e) {
            logger.info(e.getMessage());
        }
        return comment;
    }


}
