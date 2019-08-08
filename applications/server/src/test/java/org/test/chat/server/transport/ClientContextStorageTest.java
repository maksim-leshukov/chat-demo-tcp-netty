package org.test.chat.server.transport;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientContextStorageTest {

    private ClientContextStorage storage = new ClientContextStorage();

    @Test(expected = RuntimeException.class)
    public void getWhenIsEmpty() {
        storage.get();
    }

    @Test
    public void get() {
        ClientContext expectedCtx = new ClientContext();
        storage.set(expectedCtx);

        ClientContext actualCtx = storage.get();
        Assert.assertEquals(expectedCtx, actualCtx);
    }
}