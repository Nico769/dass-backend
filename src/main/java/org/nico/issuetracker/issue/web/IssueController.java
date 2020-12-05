package org.nico.issuetracker.issue.web;

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

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }


    // TODO gestire TUTTI i responseStatus in base al outcome di issueService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<IssueDto> readAll() {
        List<Issue> issues = issueService.listAllIssues();
        return issues.stream().map(IssueDto::convertToDto).collect(Collectors.toList());
    }

    // Specify params to avoid ambiguity with readAll GET.
    @GetMapping(params = "is_status")
    @ResponseStatus(HttpStatus.OK)
    List<IssueDto> readAllByIssueStatus(
            @RequestParam(value = "is_status") String status) {
        List<Issue> issues = issueService.listAllIssuesByStatus(status);
        return issues.stream().map(IssueDto::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    IssueDto createIssue(@RequestBody IssueDto receivedIssueDto) {
        Issue receivedIssue = IssueDto.convertToEntity(receivedIssueDto);
        Issue createdIssue = issueService.addNewIssue(receivedIssue);
        return IssueDto.convertToDto(createdIssue);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    IssueDto readIssue(@PathVariable UUID uuid) {
        return IssueDto.convertToDto(issueService.getIssueByUuid(uuid));
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    IssueDto partialUpdateIssue(@PathVariable UUID uuid, @RequestBody IssueDto receivedIssueDto) {
        Issue receivedIssue = IssueDto.convertToEntity(receivedIssueDto);
        Issue patchedIssue = issueService.updateIssue(uuid, receivedIssue);
        return IssueDto.convertToDto(patchedIssue);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteIssue(@PathVariable UUID uuid) {
        issueService.removeIssueByUuid(uuid);
    }
}
