package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Comment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.History;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBugPersistenceService extends Serializable {
    /**
     * @return a list of bugs which is in descending order by target date.
     */
    List<Bug> getAllBugs();


    /**
     * Method for adding a bug
     *
     * @param bug
     * @return optional of the added bug
     */
    Optional<Bug> addBug(Bug bug,Attachment attachment);


    /**
     * Method for finding a bug
     *
     * @param id
     * @return optional of the bug or empty if doesn't exist
     */
    Optional<Bug> findBugById(long id);


    /**
     * Method for adding an attachment
     *
     * @param attachment
     * @return optional of the attachment
     */
    Optional<Attachment> addAttachment(Attachment attachment);

    /**
     * Method for getting all the attachments from db
     *
     * @param
     * @return list of founded attachments
     */
    List<Attachment> getAllAttachments();


    /**
     * Method for updating a bug
     *
     * @param bug
     * @return optional of the updated bug
     */
    Optional<Bug> updateBug(Bug bug);

    /**
     * Method for getting all the comments that has been added for a bug
     * @param bug
     * @return list of comments for the bug that is sent as parameter
     */
    List<Comment> getCommentsForBug(Bug bug);

    /**
     * Method used for deleting an existing attachment
     * @param attachment
     * @return optional of the deleted attachment
     */
    Optional<Attachment> deleteAttachment(Attachment attachment);

    /**
     * Method used for adding in DB a commment
     * @param comment
     * @return optional of the added comment
     */
    Optional<Comment> addComment(Comment comment);

    /**
     * Method used for adding in DB a history entity
     * @param history
     * @return optional of the added history entity
     */
    Optional<History> addHistory(History history);

    /**
     * Method used for getting all the records from history table from DB
     * @return all history entities from db
     */
    List<History> getAllHistory();

    /**
     * Method used for generating a report that contains the bug statuses
     * @return a map with the statues as keys and the number of apperances as values
     */
    Map<String,Long> getStatistics();

    /**
     * Method used for getting the number of bugs each user has been fixed
     * @return a map with the usernames of the users as keys
     * and number of fixed bugs as values
     */
    Map<String,Long> getFixedBugsForUser();

    /**
     * Method used for getting the number bugs that has been created and rejected
     * @return a map with statues:NEW and REJECTED as keys
     * and number of bugs for each status as values
     */
    Map<String, Long> getStatisticsForNewAndRejectedBugs();
}
