package undo.utils;

/**
 * Basic interface of a Stack, based on Java's implementation and documentation.
 *
 * @param <T> class of the Stack items.
 */
public interface Stack<T> {
  /**
   * Check whether the stack is empty.
   *
   * @return a boolean whether the stack is empty or not.
   */
  boolean empty();

  /**
   * Checks for the object on the top of the stack without removing it.
   *
   * @return the object on top of the stack.
   */
  T peek();

  /**
   * Checks for the object on the top of the stack and removes it.
   *
   * @return the object on top of the stack.
   */
  T pop();

  /**
   * Pushes an object on top of the stack.
   *
   * @param t the object to be pushed on top of the stack.
   * @return the pushed object.
   */
  T push(T t);

  /**
   * Checks if the object is in the stack and determines the distance to the top of the stack.
   *
   * @param t the object to search for.
   * @return the distance to the top of the stack. If not found -1.
   */
  int search(T t);
}
