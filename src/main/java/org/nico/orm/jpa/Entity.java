package org.nico.orm.jpa;

public interface Entity<T extends EntityId> {
    T getId();
}
