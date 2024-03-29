package org.nico.issuetracker.issue.web;

import lombok.Value;
import org.nico.issuetracker.issue.Issue;

import java.util.UUID;

import static org.nico.issuetracker.issue.Issue.IssueStatus;

// Implementation of a DTO (Data Transfer Object) pattern for the Issue entity.
// In this case, the pattern prevents leaking the Issue's id field to the clients.
@Value
public class IssueDto {
    UUID uuid;
    String title;
    String description;
    IssueStatus status;

    public static IssueDto convertToDto(Issue issue) {
        return new IssueDto(issue.getUuid(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getStatus());
    }

    public static Issue convertToEntity(IssueDto issueDto) {
        return new Issue(issueDto.getUuid(),
                issueDto.getTitle(),
                issueDto.getDescription(),
                issueDto.getStatus());
    }
}
