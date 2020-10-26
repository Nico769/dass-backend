package org.nico.issuetracker.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.nico.issuetracker.issue.Issue.IssueStatus;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public List<Issue> listAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public List<Issue> listAllIssuesByStatus(String status) {
        if (status.equalsIgnoreCase(IssueStatus.OPEN.toString()))
            return issueRepository.findAllWhereIssueStatusIsOpen();
        else
            return issueRepository.findAllWhereIssueStatusIsClosed();
    }

    @Override
    public Issue getIssueByUuid(UUID uuid) {
        return issueRepository.findByUuid(uuid).orElseThrow();
    }

    @Override
    public Issue addNewIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateIssue(UUID uuid, Issue newIssue) {
        Issue toUpdateIssue = issueRepository.findByUuid(uuid).orElseThrow();
        String newIssueTitle = newIssue.getTitle();
        String newIssueDescription = newIssue.getDescription();
        IssueStatus newIssueStatus = newIssue.getStatus();

        if (newIssueTitle != null)
            toUpdateIssue.setTitle(newIssueTitle);

        if (newIssueDescription != null)
            toUpdateIssue.setDescription(newIssueDescription);

        if (newIssueStatus != null)
            toUpdateIssue.setStatus(newIssueStatus);

        return issueRepository.save(toUpdateIssue);
    }

    @Override
    public void removeIssueByUuid(UUID uuid) {
        try {
            // For some reasons I can't get the repository to perform a deletion by UUID.
            // Thus, I'll fallback to a deleteById
            Optional<Issue> toDeleteIssue = issueRepository.findByUuid(uuid);
            toDeleteIssue.ifPresent(issue -> issueRepository.deleteById(issue.getId()));
        }
        // There is nothing to do since the resource does not exist.
        catch (EmptyResultDataAccessException ignored) {}
    }
}
