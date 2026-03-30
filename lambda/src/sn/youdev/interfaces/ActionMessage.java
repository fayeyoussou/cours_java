package sn.youdev.interfaces;

import java.util.Objects;

/**
 * Interface fonctionnelle pour executer une action a partir d'un message.
 */
@FunctionalInterface
public interface ActionMessage {
    void executer(String faty);

    default ActionMessage puis(ActionMessage suivante) {
        Objects.requireNonNull(suivante);
        return message -> {
            executer(message);
            suivante.executer(message);
        };
    }
}
