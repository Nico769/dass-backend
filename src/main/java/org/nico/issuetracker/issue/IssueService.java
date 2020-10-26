package org.nico.issuetracker.issue;

import java.util.List;
import java.util.UUID;

public interface IssueService {
    List<Issue> listAllIssues();
    List<Issue> listAllIssuesByStatus(String status);
    Issue getIssueByUuid(UUID uuid);
    Issue addNewIssue(Issue issue);
    Issue updateIssue(UUID uuid, Issue newIssue);
    void removeIssueByUuid(UUID uuid);
}
