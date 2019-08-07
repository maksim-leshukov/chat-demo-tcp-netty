package com.miro.leshukovma.chat.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum  DataMessageType {
    @JsonProperty("SEND_MESSAGE")
    SendMessage,
    LoginAs,
    Logout,
    GetCommands,
    ExecuteCommand,

    CommandsList,
    LoginAccepted,
    LoginNotAccepted,
    NewMessage,
    CommandExecutionResult,
    LastMessages,
}
