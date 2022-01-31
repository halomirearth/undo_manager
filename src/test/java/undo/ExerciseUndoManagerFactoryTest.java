package undo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


import org.junit.Test;

/**
 * This test and verifies the creation of an {@link UndoManager} by the {@link UndoManagerFactory}.
 */
public class ExerciseUndoManagerFactoryTest {
  private static final int DEFAULT_BUFFER_SIZE = 5;

  @Test
  public void testSuccessfulFactory() {
    UndoManagerFactory undoManagerFactory = new ExerciseUndoManagerFactory();
    UndoManager undoManager = undoManagerFactory.createUndoManager(mock(Document.class),
        DEFAULT_BUFFER_SIZE);
    assertNotNull(undoManager);
  }
}
