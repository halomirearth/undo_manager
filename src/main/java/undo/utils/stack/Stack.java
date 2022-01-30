package undo.utils.stack;

/**
 * Basic interface of a Stack's functionality.
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
   * Checks if the object is in the stack and determines the
   * distance to the top of the stack. (1-based position)
   *
   * @param t the object to search for.
   * @return the distance to the top of the stack. If not found -1. If on top 1.
   */
  int search(T t);

  /**
   * Returns the current size of the stack.
   *
   * @return the number of elements in the stack.
   */
  int size();

  /**
   * Removes all entries from the stack.
   */
  void clear();
}
