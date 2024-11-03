package pt.psoft.g1.psoftg1.shared.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.shared.model.Name;

@Component
public class NameFactory {
    public Name newName(String name)
    {
        return new Name(name);
    }
}
