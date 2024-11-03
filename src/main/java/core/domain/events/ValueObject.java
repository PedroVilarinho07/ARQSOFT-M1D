/*package core.domain.events;

import core.domain.events.ValueObjectProps;

import java.util.Objects;

public abstract class ValueObject<T extends ValueObjectProps> {
    protected final T props;

    protected ValueObject(T props) {
        this.props = props;
    }
    public boolean equals(ValueObject<T> vo) {
        if (vo == null || vo.props == null) {
            return false;
        }
        return Objects.equals(this.props, vo.props);
    }

    @Override
    public int hashCode() {
        return Objects.hash(props);
    }
}
*/