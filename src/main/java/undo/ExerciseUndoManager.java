package undo;


import undo.utils.stack.DropoutStack;
import undo.utils.stack.Stack;

public class ExerciseUndoManager implements UndoManager {
  private final Stack<Change> availableUndos;
  private final Stack<Change> availableRedos;
  private final Document document;

  public ExerciseUndoManager(Document document, int bufferSize) {
    this.document = document;
    this.availableUndos = new DropoutStack<>(bufferSize);
    this.availableRedos = new DropoutStack<>(bufferSize);
  }

  @Override
  public void registerChange(Change change) {
    this.availableUndos.push(change);
    this.availableRedos.clear();
  }

  @Override
  public boolean canUndo() {
    return !this.availableUndos.empty();
  }

  @Override
  public void undo() throws IllegalStateException {
    if (!canUndo()) {
      throw new IllegalStateException("Nothing to undo.");
    }
    Change peekChange = this.availableUndos.peek();
    try {
      peekChange.revert(this.document);
    } catch (Exception e) {
      throw new IllegalStateException("Error when trying to revert change.");
    }
    Change poppedChange = this.availableUndos.pop();
    this.availableRedos.push(poppedChange);
  }

  @Override
  public boolean canRedo() {
    return !this.availableRedos.empty();
  }

  @Override
  public void redo() {
    if (!canRedo()) {
      throw new IllegalStateException("Nothing to redo.");
    }
    Change peekedChange = this.availableRedos.peek();
    try {
      peekedChange.apply(this.document);
    } catch (Exception e) {
      throw new IllegalStateException("Error when trying to apply change.");
    }
    Change poppedChange = this.availableRedos.pop();
    this.availableUndos.push(poppedChange);
  }
}
