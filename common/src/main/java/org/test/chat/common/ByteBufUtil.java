package org.test.chat.common;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class ByteBufUtil {

    public static String getJsonString(ByteBuf in) {
        int bytesCount = in.readableBytes();
        CharSequence charSequence = in.getCharSequence(0, bytesCount, Charset.forName("UTF-8"));
        return charSequence.toString();
    }

}
