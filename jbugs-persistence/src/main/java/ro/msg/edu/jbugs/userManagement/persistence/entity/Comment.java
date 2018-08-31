package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@NamedQueries({
        @NamedQuery(name = Comment.GET_COMMENTS_FOR_BUG, query = "select c from Comment c where c.bug= :bugId")
})
public class Comment {

    @Transient
    private final static int MAX_STRING_LENGTH = 40;
    public final static String GET_COMMENTS_FOR_BUG = "get all comments for a bug";
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    @ManyToOne
    @JoinColumn(name = "idBug", nullable = false)
    public Bug bug;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    public User user;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", length = MAX_STRING_LENGTH, nullable = false)
    private Date date;


    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
