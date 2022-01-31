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
  public void undo() {

  }

  @Override
  public boolean canRedo() {
    return !this.availableRedos.empty();
  }

  @Override
  public void redo() {

  }
}
