package org.nico.orm.jpa;

import java.io.Serializable;

public interface EntityId<T> extends Serializable {

    T getId();

    // Not using toString because that is usually
    // for debugging purposes while we are writing application logic here
    String asString();
}
