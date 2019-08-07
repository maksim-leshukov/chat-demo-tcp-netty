package org.test.chat.common.message;

import org.test.chat.common.DataMessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayloadMessageType {
    DataMessageType value();
}
