package com.miro.leshukovma.chat.client.connection_state_machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SpringConnectionStateMachine implements ConnectionStateMachine {

    @Autowired
    private StateMachine<ClientState, StateEvent> stateMachine;

    @PostConstruct
    public void init() {
        stateMachine.start();
    }


    @Override
    public void toState(StateEvent stateEvent) {
        stateMachine.sendEvent(stateEvent);
    }
}
