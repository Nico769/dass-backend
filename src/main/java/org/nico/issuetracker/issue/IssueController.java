package org.nico.issuetracker.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
// REST API are "contained" within /api URI
@RequestMapping("/api")
public class IssueController {
    private final IssueRepository issueRepository;

    // Autowired is redundant if there is only one constructor
    @Autowired
    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping("/issues")
    List<Issue> listAll() {
        return issueRepository.findAll();
    }

    @GetMapping("/issues/{status}")
    List<Issue> listAllByIssueStatus(@PathVariable String status) {
        if (status.equals(Issue.IssueStatus.OPEN.toString()))
            return issueRepository.findAllWhereIssueStatusIsOpen();
        else
            return issueRepository.findAllWhereIssueStatusIsClosed();

    }

    @PostMapping("/issues")
    @ResponseStatus(HttpStatus.CREATED)
    void createIssue(@RequestBody Issue issue){
        issueRepository.save(issue);
    }

    @GetMapping("/issues/{id}")
    Optional<Issue> readIssue(@PathVariable Long id){
        return issueRepository.findById(id);
    }

    // RequestBody contains the updated issue
    // (i.e. the one having the id equal to PathVariable id)
    @PutMapping("/issues/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateIssue(@RequestBody Issue issue, @PathVariable Long id){
        issueRepository.save(issue);
    }

    // TODO check if no content response code is appropriate
    @DeleteMapping("/issues/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteIssue(@PathVariable Long id){
        issueRepository.deleteById(id);
    }
}
