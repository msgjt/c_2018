package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IBugPersistenceService extends Serializable {
    List<Bug> getAllBugs();
    Optional<Bug> addBug(Bug bug);
}
