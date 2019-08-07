package org.test.chat.client.connection_state_machine;

import org.test.chat.client.connection_state_machine.handlers.ConnectionEstablishedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfiguration {

    @Autowired
    private ConnectionEstablishedHandler connectionEstablishedHandler;


    @Bean
    public StateMachine<ClientState, StateEvent> stateMachine() throws Exception {
        StateMachineBuilder.Builder<ClientState, StateEvent> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(ClientState.Disconnected)
                .states(EnumSet.allOf(ClientState.class))
        ;


        builder.configureTransitions()
                .withExternal()
                    .event(StateEvent.ConnectionEstabilishd)
                    .source(ClientState.Disconnected).target(ClientState.Connected)
//                    .action(connectionEstablishedHandler)
                    .and()
                .withExternal()
                    .event(StateEvent.UserLoggedIn)
                    .source(ClientState.Connected).target(ClientState.Logged);




        return builder.build();
    }
}


