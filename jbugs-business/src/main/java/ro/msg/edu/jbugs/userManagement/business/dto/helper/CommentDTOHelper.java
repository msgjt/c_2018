package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentDTOHelper {

    public CommentDTO fromEntity(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getIdComment());
        commentDTO.setDate(comment.getDate());
        commentDTO.setText(comment.getText());
        commentDTO.setUser(comment.getUser().getUsername());
        return commentDTO;
    }

}
