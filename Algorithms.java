package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms<T> {

    public static <T extends Comparable<T>> void sort(T [] array) {
        int i = 1;
        int j;
        while (i < array.length) {
        j = i;
        while (j - 1 >= 0 && j < array.length && array[j-1].compareTo(array[j]) > 0) {
            swap(array, j, j-1);
            j--;
        }
        i++;
      }
    }

    public static <T> void reverse(T [] array) {
        for(int i=0; i<(array.length/2); i++) {
            swap(array, i, array.length-1-i);
        }
    }

    public static <T> void swap(T [] array, int first, int second) {
        T tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }

    public static class ModeSearchResult<T> {
        public T theMode;
        public int count = 0;
     }
  
    public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array) {
        ModeSearchResult<T> result = new ModeSearchResult<>();
        result.count = -1;
        if (array == null || array.length < 2) {
            return result;
        }
        sort(array);
        result.theMode = array[0];
        result.count = 1;
        int topfrequency = 1;
        int tempFrequency = 1;
        int index = 1;
        while (index < array.length) {
            if (array[index].compareTo(array[index-1]) == 0) {
                tempFrequency++;
            } else {
                if (tempFrequency > topfrequency) {
                    topfrequency = tempFrequency;
                    result.count = topfrequency;
                    result.theMode = array[index-1];
                }
                tempFrequency = 1;
            }
            index++;
        }
        if (tempFrequency > topfrequency) {
            result.count = tempFrequency;
            result.theMode = array[index-1];
        }
        return result;
    }
    
    public static <T> int partitionByRule(T [] array, int count, Predicate<T> rule) {
        int index = 0;
        for (; index<count; index++) {
            if (rule.test(array[index])) {
                break;
            }
        }
        if (index >= count) {
            return count;
        }
        int nextIndex = index+1;
        while (nextIndex < count) {
            if (rule.test(array[nextIndex]) == false) {
                swap(array, index, nextIndex);
                index++;
            }
            nextIndex++;
        }
        return index;
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        // TODO Implement this in step 2.
        int index = (fromIndex + toIndex) / 2;
        if (aValue.equals(fromArray[index])) {
            return index;
        }
        while (fromIndex <= toIndex) {
            index = (fromIndex + toIndex)/2;
            if (aValue.compareTo(fromArray[index]) < 0) {
                toIndex = index - 1;
            } else if (aValue.compareTo(fromArray[index]) > 0) {
                fromIndex = index + 1;
            } else {
                break;
            }
        }
        if (aValue.equals(fromArray[index])) {
            return index;
        }
        return -1;
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int low, int high) {
        if (low < high) {
            int partitionindex = partition(array, low, high);
            quickSort(array, low, partitionindex - 1);
            quickSort(array, partitionindex + 1, high);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        int i = low - 1;
        for (int j=low; j<high; j++) {
            if (array[j].compareTo(array[high]) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

}