package org.nico.issuetracker.issue.web;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.nico.issuetracker.issue.Issue;
import org.nico.issuetracker.issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
// Issue-related REST-APIs are "contained" within /api/issues URL
@RequestMapping(path = "/api/issues")
@CrossOrigin("*")
public class IssueController {
    private final IssueService issueService;

    // Autowired is redundant if there is only one constructor
    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }


    // TODO gestire TUTTI i responseStatus in base al outcome di issueService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<IssueDto> readAll() {
        List<Issue> issues = issueService.listAllIssues();
        // Transform the retrieved issues to DTO objects.
        // This prevents leaking the Issue's id field.
        return issues.stream().map(IssueDto::fromIssue).collect(Collectors.toList());
    }

    // Specify params to avoid ambiguity with readAll GET.
    @GetMapping(params = "is_status")
    @ResponseStatus(HttpStatus.OK)
    List<IssueDto> readAllByIssueStatus(
            @RequestParam(value = "is_status") String status) {
        List<Issue> issues = issueService.listAllIssuesByStatus(status);
        return issues.stream().map(IssueDto::fromIssue).collect(Collectors.toList());
    }

    // @RequestBody expects an entire Entity in the body.
    // I'll just use Jackson ObjectNode for now.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    IssueDto createIssue(@RequestBody ObjectNode rcvJson){
        Issue newIssue = issueService.addNewIssue(
                UUID.fromString(rcvJson.get("uuid").asText()),
                rcvJson.get("title").asText(),
                rcvJson.get("description").asText());
        return IssueDto.fromIssue(newIssue);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    IssueDto readIssue(@PathVariable UUID uuid){
        return IssueDto.fromIssue(issueService.getIssueByUuid(uuid));
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    IssueDto partialUpdateIssue(@PathVariable UUID uuid, @RequestBody Issue patchedIssue){
        return IssueDto.fromIssue(issueService.updateIssue(uuid, patchedIssue));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteIssue(@PathVariable UUID uuid){
        issueService.removeIssueByUuid(uuid);
    }
}
