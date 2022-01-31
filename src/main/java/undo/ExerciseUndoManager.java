package undo;

public class ExerciseUndoManager implements UndoManager {
  private final Document document;

  public ExerciseUndoManager(Document document, int bufferSize) {
    this.document = document;
    this.bufferSize = bufferSize;
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
