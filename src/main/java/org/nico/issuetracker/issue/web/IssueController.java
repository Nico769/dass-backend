package org.nico.issuetracker.issue.web;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.nico.issuetracker.issue.Issue;
import org.nico.issuetracker.issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Issue-related REST-APIs are "contained" within /api/issues URL
@RequestMapping(path = "/api/issues")
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
    List<Issue> readAll() {
        return issueService.listAllIssues();
    }

    // Specify params to avoid ambiguity with readAll GET.
    @GetMapping(params = "is_status")
    @ResponseStatus(HttpStatus.OK)
    List<Issue> readAllByIssueStatus(
            @RequestParam(value = "is_status") String status) {
        return issueService.listAllIssuesByStatus(status);
    }

    // @RequestBody expects an entire Entity in the body.
    // I'll just use Jackson ObjectNode for now.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Issue createIssue(@RequestBody ObjectNode rcvJson){
        return issueService.addNewIssue(
                rcvJson.get("title").asText(),
                rcvJson.get("description").asText());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Issue readIssue(@PathVariable Long id){
        return issueService.getIssueById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Issue partialUpdateIssue(@PathVariable Long id, @RequestBody Issue patchedIssue){
        return issueService.updateIssue(id, patchedIssue);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteIssue(@PathVariable Long id){
        issueService.removeIssueById(id);
    }
}
