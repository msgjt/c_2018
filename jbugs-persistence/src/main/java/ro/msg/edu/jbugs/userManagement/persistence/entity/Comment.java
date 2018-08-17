package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    @Transient
    private final static int MAX_STRING_LENGTH = 40;
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    @ManyToOne
    @JoinColumn(name = "idBug", nullable = false)
    public Bug bug;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    public User user;

    @Column(name = "text", length = MAX_STRING_LENGTH, nullable = false)
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
