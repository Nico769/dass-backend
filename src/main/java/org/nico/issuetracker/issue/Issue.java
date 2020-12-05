package org.nico.issuetracker.issue;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
public class Issue {
    public enum IssueStatus {
        OPEN,
        CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotNull
    private final UUID uuid;
    @NotNull
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    public Issue(UUID uuid, String title, String description, IssueStatus status) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
