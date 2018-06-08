public class Heap<T extends Comparable<T>> {
    private T[] heap;
    private int currentPosition = -1;

    @SuppressWarnings({"unchecked"})
    public Heap(int size) {
        heap = (T[])(new Comparable[size]);
    }

    public void insert(T item) {
        if (isFull()) {
            throw new RuntimeException("Heap is full...");
        }

        heap[++currentPosition] = item;

        fixUp(currentPosition);
    }

    public T getMax() {
        T result = heap[0];

        heap[0] = heap[currentPosition];
        heap[currentPosition] = null;
        currentPosition--;

        fixDown(0, currentPosition);

        return result;
    }

    private void fixUp(int index) {
        int parentIndex = (index - 1) / 2;

        T tmp = heap[index];

        while (index > 0 && (tmp.compareTo(heap[parentIndex]) > 0)) {
            heap[index] = heap[parentIndex];

            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }

        heap[index] = tmp;
    }

    private void fixDown(int index, int upTo) {
        while (index <= upTo) {
            int indexLeftChild = (2 * index) + 1;
            int indexRightChild = (2 * index) + 2;

            if (indexLeftChild <= upTo) {
                int indexChildToSwap;

                if (indexRightChild > upTo) {
                    indexChildToSwap = indexLeftChild;
                } else {
                    indexChildToSwap = (heap[indexLeftChild].compareTo(heap[indexRightChild]) > 0) ? indexLeftChild : indexRightChild;
                }

                if (heap[index].compareTo(heap[indexChildToSwap]) < 0) {
                    swap(index, indexChildToSwap);
                } else {
                    break;
                }

                index = indexChildToSwap;
            } else {
                break;
            }
        }
    }

    public void heapSort() {
        for (int i = currentPosition; i >= 0; i--) {
            swap(0, i); 
            fixDown(0, i - 1);

            System.out.print(heap[i] + " ");
        }
    }

    private boolean isFull() {
        return currentPosition == (heap.length - 1);
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}