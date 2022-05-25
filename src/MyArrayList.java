public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private static final int factor = 2;
    private static final int initial = 3;

    private Object[] arr;
    private int length = 0;
    private int capacity = initial;

    public MyArrayList() {
        arr = new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object o) {
        for (Object ob : arr)
            if (ob == o)
                return true;
        return false;
    }

    @Override
    public void add(T item) {
        if (length == capacity)
            increaseCapacity();
        arr[length++] = item;
    }

    public void increaseCapacity() {
        capacity *= factor;
        Object[] arr1 = new Object[capacity];
        System.arraycopy(arr, 0, arr1, 0, arr.length);
        arr = arr1;
    }

    @Override
    public void add(T item, int index) {
        if(index < length){
            arr[index] = item;
        }
        else if(index == length){
            this.add(item);
        }
        else{
            while (index >= capacity)
                increaseCapacity();
            Object[] arr1 = new Object[capacity];
            System.arraycopy(arr, 0, arr1, 0, length);

            for(int i=length; i<index; i++)
                arr1[length + i] = null;
            arr1[index] = item;

            length = index+1;
            arr = arr1;
        }
    }

    @Override
    public boolean removeItem(T item) {
        int index = -1;

        for (int i = 0; i < length; i++) {
            if (arr[i] == item) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T remove(int index) {
        Object[] res = new Object[length];
        Object o = null;

        for (int i = 0, j = 0; i < length; i++) {
            if (i == index) {
                j = 1;
                o = arr[i];
                continue;
            }
            res[i - j] = arr[i];
        }

        length -= 1;
        arr = res;
        return (T) o;
    }

    @Override
    public void clear() {
        length = 0;
        capacity = initial;
        arr = new Object[capacity];
    }

    @Override
    public T get(int index) {
        return (T) arr[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++)
            if (arr[i] == o)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < length; i++)
            if (arr[i] == o)
                lastIndex = i;
        return lastIndex;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length - 1; i++){
            for (int j = 0; j < length - i - 1; j++){
                T c = this.get(j);
                T d = this.get(j + 1);
                if (first_greater(c, d)) {
                    this.add(d, j);
                    this.add(c,j+1);
                }
            }
        }
    }

    private boolean first_greater(T o1, T o2){
        return o1.compareTo(o2) > 0;
    }
}