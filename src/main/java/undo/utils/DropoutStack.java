package undo.utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class DropoutStack<T> implements Stack<T>{
  private final Deque<T> dropoutStack = new ArrayDeque<>();


  @Override
  public boolean empty() {
    return this.dropoutStack.isEmpty();
  }

  @Override
  public T peek() {
    return this.dropoutStack.peek();
  }

  @Override
  public T pop() {
    return this.dropoutStack.pop();
  }

  @Override
  public T push(T t) {
    this.dropoutStack.push(t);
    return this.peek();
  }

  @Override
  public int search(T t) {
    return 0;
  }
}
