/* Adapted from here https://spring.io/guides/gs/accessing-data-rest/ */

package org.nico.issuetracker.issue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findAll();

    default List<Issue> findAllWhereIssueStatusIsOpen() {
        return findAllByStatus(Issue.IssueStatus.OPEN);
    }

    default List<Issue> findAllWhereIssueStatusIsClosed() {
        return findAllByStatus(Issue.IssueStatus.CLOSED);
    }

    List<Issue> findAllByStatus(Issue.IssueStatus status);

}
