import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

public class MyArrayList<E> {

    private static final Logger logger = Logger.getGlobal();

    private E[] myArrayList;
    // where size = number of elements in array; size != array's length
    private int size = 0;

    public MyArrayList() {
        this.myArrayList = (E[]) new Object[10];
    }

    public MyArrayList(Collection<? extends E> collection) {
        this.myArrayList = (E[]) collection.toArray();
        if (myArrayList.length == 0) {
            size = 0;
        } else {
            for (int i = 0; i < myArrayList.length; i++) {
                if (myArrayList[i] == null) {
                    size = i;
                    break;
                }
            }
        }
    }

    public MyArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("The array's initial capacity cannot be less than zero.");
        } else {
            this.myArrayList = (E[]) new Object[initialCapacity];
        }
    }

    public E[] getMyArrayList() {
        return myArrayList;
    }

    public boolean add(E element) {
        ensureCapacity();
        myArrayList[size] = element;
        size++;
        return true;
    }

    public void add(int index, E element) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        ensureCapacity();
        if (myArrayList.length > 1) {
            shiftElementsFromIndexToSize(index);
        }
        myArrayList[index] = element;
        size++;
    }

    public E get(int index){
        return myArrayList[index];
    }

    public E remove(int index) {
        if (index < 0 || index >= myArrayList.length) {
            if (capacity() == 0) {
                logger.info("Array length is zero. There are no elements to delete in this array.");
            } else {
                logger.info("Index out of bounds: " + index);
            }
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E elementRemoved = myArrayList[index];
        if (! (index == size - 1)) {
            shiftElementsFromSizeToIndex(index);
        }
        myArrayList[size - 1] = null;
        size--;
        return elementRemoved;
    }

    public void remove(E element) {
        this.remove(this.indexOf(element)) ;
    }

    public E set(int index, E element) {
        E elementPreviouslyAtIndex = myArrayList[index];
        if (index < 0 || index >= myArrayList.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        myArrayList[index] = element;
        return elementPreviouslyAtIndex;
    }

    public void clear() {
        this.myArrayList = (E[]) new Object[0];
        this.size = 0;
    }

    public boolean isEmpty() {
        if (myArrayList.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(E elementToFind) {
        if (isEmpty()) {
            return false;
        }
        for (E elementInList : myArrayList) {
            if (elementInList == elementToFind) {
                return true;
            }
        }
        return false;
    }

    public int capacity() {
        return myArrayList.length;
    }

    public int size() {
        return size;
    }

    public void ensureCapacity() {
        if (myArrayList.length == 0) {
            myArrayList = (E[]) new Object[1];
        } else if (size == myArrayList.length) {
            doubleArrayCapacity();
        }
    }

    public void doubleArrayCapacity() {
        E[] arrayWithDoubleCapacity = (E[]) new Object[size * 2];
        System.arraycopy(myArrayList, 0, arrayWithDoubleCapacity, 0, size);
        myArrayList = arrayWithDoubleCapacity;
    }

    public void shiftElementsFromIndexToSize(int index) {
        for (int i = size; i > index; i--) {
            myArrayList[i] = myArrayList[i - 1];
        }
    }

    public void shiftElementsFromSizeToIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            myArrayList[i] = myArrayList[i + 1];
        }
    }

    public Object[] toArray() {
        return Arrays.copyOf(myArrayList, size);
    }

    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < this.myArrayList.length; i++) {
                if (this.myArrayList[i] == element) {
                    return i;
                }
            }
        }

        for (int i = 0; i < this.myArrayList.length; i++) {
            if (this.myArrayList[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }
}
