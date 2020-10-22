package org.nico.orm.jpa;

import java.util.Objects;

import org.nico.util.ArtifactForFramework;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.MoreObjects.toStringHelper;

@MappedSuperclass
public abstract class AbstractEntity<T extends EntityId> implements Entity<T> {

    @EmbeddedId
    private T id;

    @ArtifactForFramework
    protected AbstractEntity() {
    }

    public AbstractEntity(T id) {
        this.id = checkNotNull(id);
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof AbstractEntity) {
            AbstractEntity other = (AbstractEntity) obj;
            result = Objects.equals(id, other.id);
        }

        return result;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
