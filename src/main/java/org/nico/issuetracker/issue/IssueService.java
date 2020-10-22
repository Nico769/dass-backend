package org.nico.issuetracker.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }


}
