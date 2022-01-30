package undo.utils;

import static org.mockito.Mockito.mock;


import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import undo.utils.stack.DropoutStack;
import undo.utils.stack.Stack;

public class DropoutStackTest {
  private List<Object> mockObjects;
  private Stack<Object> dropoutStack;
  private final int STACK_MAX_SIZE = 5;

  @Before
  public void setup() {
    final int numberOfObjectToCreate = 10;
    mockObjects = new ArrayList<>();
    dropoutStack = new DropoutStack<>(STACK_MAX_SIZE);
    for (int i = 0; i < numberOfObjectToCreate; i++) {
      mockObjects.add(mock(Object.class));
    }
    for (Object object : mockObjects) {
      addNewEntryInStack(object);
    }
  }

  private void addANumberOfNewEntriesInStack(int amount) {
    for (int i = 0; i < amount; i ++) {
      this.addNewEntryInStack(mock(Object.class));
    }
  }

  private void addNewEntryInStack(Object entry) {
    dropoutStack.push(entry);
  }

  @Test
  public void testNotExceedingMaxSize() {
    Assert.assertEquals(STACK_MAX_SIZE, dropoutStack.size());
  }

  @Test
  public void testClear() {
    Assert.assertEquals(STACK_MAX_SIZE, dropoutStack.size());
    dropoutStack.clear();
    Assert.assertTrue(dropoutStack.empty());
  }

  @Test
  public void testPop() {
    Object poppedMockedObject = dropoutStack.pop();
    Assert.assertEquals(STACK_MAX_SIZE - 1, dropoutStack.size());
    Assert.assertEquals(mockObjects.get(mockObjects.size() - 1), poppedMockedObject);
  }

  @Test
  public void testPeek() {
    Object peekedMockedObject = dropoutStack.peek();
    Assert.assertEquals(STACK_MAX_SIZE, dropoutStack.size());
    Assert.assertEquals(mockObjects.get(mockObjects.size() - 1), peekedMockedObject);
  }

  @Test
  public void testSearchAndDropout() {
    Object objectToWatch = mockObjects.get(mockObjects.size()-1);
    Assert.assertEquals(objectToWatch, dropoutStack.peek());
    Assert.assertEquals(1, dropoutStack.search(objectToWatch));
    this.addANumberOfNewEntriesInStack(2);
    Assert.assertEquals(3, dropoutStack.search(objectToWatch));
    this.addANumberOfNewEntriesInStack(2);
    Assert.assertEquals(5, dropoutStack.search(objectToWatch));
    this.addANumberOfNewEntriesInStack(1);
    Assert.assertEquals(-1, dropoutStack.search(objectToWatch));
  }
}
