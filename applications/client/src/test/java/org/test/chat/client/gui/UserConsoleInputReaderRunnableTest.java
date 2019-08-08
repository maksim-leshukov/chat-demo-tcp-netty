package org.test.chat.client.gui;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.concurrent.*;

import static org.mockito.Mockito.*;

public class UserConsoleInputReaderRunnableTest {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    UserInputHandlerKeeper handlerKeeper = mock(UserInputHandlerKeeper.class);
    ConsoleReaderProvider consoleReaderProvider = mock(ConsoleReaderProvider.class);

    @After
    public void tearDown() {
        executor.shutdownNow();
    }

    @Test
    public void listenConsole() {
        doReturn(new StringReader("Hello1\n")).when(consoleReaderProvider).getConsoleReader();

        UserConsoleInputReaderRunnable inputReader = new UserConsoleInputReaderRunnable(null);
        inputReader.consoleReaderProvider = consoleReaderProvider;
        inputReader.userInputHandlerKeeper = handlerKeeper;

        verifyDispatchUserInput(inputReader, "Hello1");
    }

    private void verifyDispatchUserInput(UserConsoleInputReaderRunnable inputReader, String expectedInput) {
        Future<?> future = executor.submit(inputReader);

        try {
            future.get(200, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
        }

        future.cancel(true);

        verify(handlerKeeper).dispatch(eq(expectedInput));
    }

    @Test
    public void listenSystemIn() {
        UserConsoleInputReaderRunnable inputReader = new UserConsoleInputReaderRunnable(new ByteArrayInputStream("Hello2\n".getBytes()));
        inputReader.consoleReaderProvider = consoleReaderProvider;
        inputReader.userInputHandlerKeeper = handlerKeeper;

        verifyDispatchUserInput(inputReader, "Hello2");
    }
}