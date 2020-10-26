/* Adapted from here https://spring.io/guides/gs/accessing-data-rest/ */

package org.nico.issuetracker.issue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.nico.issuetracker.issue.Issue.IssueStatus;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findAll();

    default List<Issue> findAllWhereIssueStatusIsOpen() {
        return findAllByStatus(IssueStatus.OPEN);
    }

    default List<Issue> findAllWhereIssueStatusIsClosed() {
        return findAllByStatus(IssueStatus.CLOSED);
    }

    List<Issue> findAllByStatus(IssueStatus status);

    Optional<Issue> findByUuid(UUID uuid);
}
