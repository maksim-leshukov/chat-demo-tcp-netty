package org.test.chat.client.connection_state_machine;

public interface ConnectionStateMachine {

    void toState(StateEvent stateEvent);

}
