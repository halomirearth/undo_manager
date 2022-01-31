package undo;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * This tests the {@link ExerciseUndoManagerFactory} and different scenarios of its behaviour.
 */
public class ExerciseUndoManagerTest {
  @Mock
  private Document mockDocument;
  private UndoManager undoManager;

  @Before
  public void setup() {
    UndoManagerFactory undoManagerFactory = new ExerciseUndoManagerFactory();
    int bufferSize = 3;
    this.undoManager = undoManagerFactory.createUndoManager(mockDocument, bufferSize);
  }

  private Change createMockChange() {
    return mock(Change.class);
  }

  @Test
  public void testCanUndoEmpty() {
    Assert.assertFalse(undoManager.canUndo());
  }

  @Test
  public void testCanRedoEmpty() {
    Assert.assertFalse(undoManager.canRedo());
  }

  @Test(expected = IllegalStateException.class)
  public void testUndoExceptionOnEmpty() {
    undoManager.undo();
  }

  @Test(expected = IllegalStateException.class)
  public void testRedoExceptionRevert() {
    undoManager.redo();
  }

  @Test(expected = IllegalStateException.class)
  public void testUndoExceptionRevert() {
    Change change = createMockChange();
    doThrow(IllegalStateException.class).when(change).revert(any());
    undoManager.registerChange(change);
    undoManager.undo();
  }

  @Test(expected = IllegalStateException.class)
  public void testRedoExceptionApply() {
    Change change = createMockChange();
    doThrow(IllegalStateException.class).when(change).apply(any());
    undoManager.registerChange(change);
    undoManager.undo();
    undoManager.redo();
  }

  @Test
  public void testUndo() {
    Change change1 = createMockChange();
    Change change2 = createMockChange();
    Change change3 = createMockChange();
    Change change4 = createMockChange();

    undoManager.registerChange(change1);
    undoManager.registerChange(change2);
    undoManager.registerChange(change3);
    undoManager.registerChange(change4);

    undoManager.undo();
    undoManager.undo();
    undoManager.undo();
    Assert.assertFalse(undoManager.canUndo());
    Assert.assertThrows(IllegalStateException.class, () -> undoManager.undo());
  }

  @Test
  public void testRedo() {
    Change change1 = createMockChange();
    Change change2 = createMockChange();
    Change change3 = createMockChange();
    Change change4 = createMockChange();

    undoManager.registerChange(change1);
    undoManager.registerChange(change2);
    undoManager.registerChange(change3);
    undoManager.registerChange(change4);

    undoManager.undo();
    undoManager.undo();
    undoManager.undo();

    undoManager.redo();
    undoManager.redo();
    undoManager.redo();
    Assert.assertFalse(undoManager.canRedo());
    Assert.assertThrows(IllegalStateException.class, () -> undoManager.redo());
  }

  @Test
  public void testRedoRest() {
    Change change1 = createMockChange();
    Change change2 = createMockChange();
    Change change3 = createMockChange();
    Change change4 = createMockChange();

    undoManager.registerChange(change1);
    undoManager.registerChange(change2);
    undoManager.registerChange(change3);

    undoManager.undo();
    undoManager.undo();
    Assert.assertTrue(undoManager.canRedo());
    undoManager.redo();
    Assert.assertTrue(undoManager.canRedo());
    undoManager.redo();
    Assert.assertFalse(undoManager.canRedo());
    undoManager.undo();
    undoManager.undo();
    Assert.assertTrue(undoManager.canRedo());
    undoManager.registerChange(change4);
    Assert.assertFalse(undoManager.canRedo());
  }
}
