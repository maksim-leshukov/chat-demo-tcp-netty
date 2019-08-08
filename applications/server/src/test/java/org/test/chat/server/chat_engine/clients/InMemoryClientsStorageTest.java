package org.test.chat.server.chat_engine.clients;

import org.junit.Test;
import org.test.chat.server.transport.ClientContext;

import java.util.Collection;

import static org.junit.Assert.*;

public class InMemoryClientsStorageTest {

    private InMemoryClientsStorage storage = new InMemoryClientsStorage();

    @Test
    public void tryAdd() {
        boolean addFirstSuccess = storage.tryAddAs("login1", new ClientContext(), (login, clientContext) -> {});
        assertTrue(addFirstSuccess);
    }

    @Test
    public void tryAddDoubleTime() {
        boolean addFirstSuccess = storage.tryAddAs("login1", new ClientContext(), (login, clientContext) -> {});
        assertTrue(addFirstSuccess);

        boolean addSecondSuccess = storage.tryAddAs("login1", new ClientContext(), (login, clientContext) -> {});
        assertFalse(addSecondSuccess);

        assertEquals(1, storage.getClientLogins().size());
    }

    @Test
    public void testContract() {
        assertTrue(storage.getClientLogins().isEmpty());

        ClientContext expectedContext = new ClientContext();
        boolean success = storage.tryAddAs("login1", expectedContext, (login, clientContext) -> {});
        assertTrue(success);

        Collection<String> afterAddLogins = storage.getClientLogins();
        assertEquals(1, afterAddLogins.size());
        assertEquals("login1", afterAddLogins.iterator().next());

        Collection<ClientContext> clientsAfterAdd = storage.getClients();
        assertEquals(1, clientsAfterAdd.size());
        assertEquals(expectedContext, clientsAfterAdd.iterator().next());

        storage.remove("login2");
        assertEquals(1, storage.getClientLogins().size());

        storage.remove("login1");
        assertEquals(0, storage.getClientLogins().size());
    }
}