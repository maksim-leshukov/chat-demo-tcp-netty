package com.miro.leshukovma.chat.common.message;

import com.miro.leshukovma.chat.common.DataMessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayloadMessageType {
    DataMessageType value();
}
