package org.nico.issuetracker.issue;

import org.nico.orm.jpa.AbstractEntityId;

import java.util.UUID;

public class IssueId extends AbstractEntityId<UUID> {

    // Hibernate needs the protected no-args constructor
    protected IssueId() {
    }

    public IssueId(UUID id){
        super(id);
    }
}
