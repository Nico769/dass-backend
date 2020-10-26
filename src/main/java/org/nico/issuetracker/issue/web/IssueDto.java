package org.nico.issuetracker.issue.web;

import lombok.Value;
import org.nico.issuetracker.issue.Issue;

import java.util.UUID;

import static org.nico.issuetracker.issue.Issue.IssueStatus;

@Value
public class IssueDto {
    UUID uuid;
    String title;
    String description;
    IssueStatus status;

    public static IssueDto fromIssue(Issue issue){
        return new IssueDto(issue.getUuid(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getStatus());
    }
}
