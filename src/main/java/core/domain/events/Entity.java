/*package core.domain.events;

import core.domain.events.UniqueEntityID;

import java.util.Objects;

public abstract class Entity<T> {
    protected final UniqueEntityID id;
    public final T props;

    protected Entity(T props, UniqueEntityID id) {
        this.id = (id != null) ? id : new UniqueEntityID();
        this.props = props;
    }

    public UniqueEntityID getId() {
        return id;
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof Entity)) {
            return false;
        }

        if (this == object) {
            return true;
        }

        Entity<?> other = (Entity<?>) object;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
*/