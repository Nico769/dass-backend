package org.nico.issuetracker.issue;

import org.nico.orm.jpa.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "bugtracker_issue")
public class Issue extends AbstractEntity<IssueId> {
    // Java pattern for mapping an enum to a String
    public enum IssueStatus {
        OPEN("open"),
        CLOSED("closed");

        private final String status;

        IssueStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status; }
    }

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private IssueStatus status = IssueStatus.OPEN;

    // Default constructor for JPA. We don't really use it
    protected Issue() {
    }

    public Issue(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Issue(IssueId id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }

    public Issue(IssueId id, String title, String description, IssueStatus status){
        super(id);
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }
}
