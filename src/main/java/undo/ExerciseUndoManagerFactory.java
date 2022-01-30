package undo;

public class ExerciseUndoManagerFactory implements UndoManagerFactory {
  @Override
  public UndoManager createUndoManager(Document doc, int bufferSize) {
    return new ExerciseUndoManager(doc, bufferSize);
  }
}
