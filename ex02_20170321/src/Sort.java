import java.util.*;
import static java.lang.System.out;
import static java.util.Collections.swap;

public class Sort {
    public static <T extends Comparable<? super T>> 
        int ascending(T t1, T t2) {  return t1.compareTo(t2); }

    public static <T extends Comparable<? super T>> 
        int descending(T t1, T t2) { return -ascending(t1, t2); }
    
    public static <T extends Comparable<? super T>> 
        void heapSort(List<T> list) { heapSort(list, Sort::ascending); }
        
    private static final int OFFSET = 1;
        
    public static <T> void heapSort( List<T> list, Comparator<? super T> c) {
        heapTree(list, c);
        selectFromHeap(list, c);
    }
    
    private static <T> void heapTree(List<T> list, Comparator<? super T> c) {
        for(int i = 1, end = list.size() + 1; i < end; i++) { 
            bubbleLeaf(list, i, c); 
        }
    }
    
    private static <T> void selectFromHeap(List<T> list, Comparator<? super T> c) {
        for(int end = list.size(); end > OFFSET; end--) {
            swap(list, 1 - OFFSET, end - OFFSET);
            bubbleRoot(list, end, c);
        }
    }
    
    private static <T> void bubbleLeaf(List<T> list, int eleIdx, Comparator<? super T> c) {
        for(int childIdx = eleIdx, parentIdx = eleIdx / 2;
            isBubbleable(list, childIdx, parentIdx, c);
            childIdx = parentIdx, parentIdx = childIdx / 2) {
             swap(list, parentIdx - OFFSET, childIdx - OFFSET); 
        }
    }
    
    private static <T> boolean isBubbleable(List<T> list, int childIdx, int parentIdx, Comparator<? super T> c) {
        return childIdx > OFFSET && c.compare(
           list.get(parentIdx - OFFSET), list.get(childIdx - OFFSET)) < 0;
    }
    
    private static <T> void bubbleRoot(List<T> list, int end, Comparator<? super T> c) {
        for(int parentIdx = 0 + OFFSET, 
                childIdx = idxFromChilds(list, parentIdx, end, c);
            childIdx < end && 
            c.compare(list.get(childIdx - OFFSET), 
                      list.get(parentIdx - OFFSET)) > 0; 
            parentIdx = childIdx, 
            childIdx = idxFromChilds(list, parentIdx, end, c)) {
            swap(list, parentIdx - OFFSET, childIdx - OFFSET); 
        }
    }

    private static <T> int idxFromChilds(List<T> list, int parentIdx, int end, Comparator<? super T> c) {
        int childIdx = parentIdx * 2;

        return isRightLeafSuitable(list, childIdx, end, c) ? 
              childIdx + 1 : childIdx;
    }

    private static <T> boolean isRightLeafSuitable(List<T> list, int childIdx, int end, Comparator<? super T> c) {
        return childIdx < end - 1 && 
               c.compare(list.get(childIdx + 1 - OFFSET), 
                         list.get(childIdx - OFFSET)) > 0;
    }

    public static void main(String[] args) {
        List<Integer> list = 
            new ArrayList<>(Arrays.asList(10, 9, 1, 2, 5, 3, 8, 7, 12, 11));
        
        heapSort(list);
        out.println(list);
        
        heapSort(list, Sort::descending);
        out.println(list);
    }
}