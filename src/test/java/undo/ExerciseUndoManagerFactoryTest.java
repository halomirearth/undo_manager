package undo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


import org.junit.Test;

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
