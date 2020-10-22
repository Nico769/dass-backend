/* Most examples use either a long or a UUID as the primary key for an entity.
*  It is better to use a dedicated primary-key class to abstract the ID type.
* */

package org.nico.orm.jpa;

import org.nico.util.ArtifactForFramework;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

@MappedSuperclass
public abstract class AbstractEntityId<T extends Serializable> implements Serializable,
EntityId<T>{

    private T id;

    @ArtifactForFramework
    protected AbstractEntityId() {}

    protected AbstractEntityId(T id){
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public T getId(){
        return id;
    }

    @Override
    public String asString(){
        return id.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (this == obj){
            result = true;
        }
        else if (obj instanceof AbstractEntityId){
            AbstractEntityId other = (AbstractEntityId) obj;
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
