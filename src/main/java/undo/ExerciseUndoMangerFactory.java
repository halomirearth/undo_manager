package undo;

public class ExerciseUndoMangerFactory implements UndoManagerFactory {
  @Override
  public UndoManager createUndoManager(Document doc, int bufferSize) {
    return new ExerciseUndoManager(doc, bufferSize);
  }
}
