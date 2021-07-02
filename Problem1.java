
// Time - for the constructor its is O(N + L) - Integers + list of Integers, for hasNext(), next it is O(1)

// Space - O(N + D)  ArrayList size + Max depth of Recursive Stack for DFS


import java.util.NoSuchElementException;
public class NestedIterator implements Iterator<Integer> {


    private List<Integer> integers = new ArrayList<>();
    int position = 0; // Pointer to the next integer to return
    public NestedIterator(List<NestedInteger> nestedList) {

        flattenList(nestedList); // call DFS


    }

    // Using DFS iterate through the list and get the integers by unpacking
    private void flattenList(List<NestedInteger> nestedList) {

        for(NestedInteger nestedInteger: nestedList) {

            if(nestedInteger.isInteger()) {
                integers.add(nestedInteger.getInteger());
            }
            else {
                flattenList(nestedInteger.getList());
            }

        }


    }

    @Override
    public Integer next() {
        // if no element after the current then throw exception
        if(!hasNext()) throw new NoSuchElementException();

        // return the current integer at the position and increment the position
        return  integers.get(position++);

    }

    @Override
    public boolean hasNext() {

        return position < integers.size();
    }
}