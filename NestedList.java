/*The time and space complexity of this implementation is O(N)
* where N is the number of Integers*/
import java.util.*;

public class NestedIterator implements Iterator<Integer> {
    private List<Integer> flattenedList;
    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenedList = new ArrayList<>();
        flatten(nestedList);
        iterator = flattenedList.iterator();
    }

    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                flattenedList.add(nestedInteger.getInteger());
            } else {
                flatten(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger(1));
        nestedList.add(new NestedInteger(2));
        nestedList.add(new NestedInteger(Arrays.asList(new NestedInteger(3), new NestedInteger(4))));
        nestedList.add(new NestedInteger(Arrays.asList(new NestedInteger(5), new NestedInteger(6))));

        NestedIterator iterator = new NestedIterator(nestedList);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        // Output: 1 2 3 4 5 6
    }

}
