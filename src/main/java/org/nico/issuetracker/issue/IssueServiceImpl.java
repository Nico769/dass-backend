package org.nico.issuetracker.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static org.nico.issuetracker.issue.Issue.IssueStatus;

import java.util.List;

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
    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow();
    }

    @Override
    public Issue addNewIssue(String title, String description) {
        return issueRepository.save(new Issue(title, description));
    }

    @Override
    public Issue updateIssue(Long id, Issue newIssue) {
        Issue toUpdateIssue = issueRepository.findById(id).orElseThrow();
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
    public void removeIssueById(Long id) {
        // Do nothing if the resource does not exist upon a deletion request.
        try {
            issueRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ignored) {}
    }
}
