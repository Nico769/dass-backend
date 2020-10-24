package org.nico.issuetracker.issue;

import java.util.List;

public interface IssueService {
    List<Issue> listAllIssues();
    List<Issue> listAllIssuesByStatus(String status);
    Issue getIssueById(Long id);
    Issue addNewIssue(String title, String description);
    Issue updateIssue(Long id, Issue newIssue);
    void removeIssueById(Long id);
}
