// Time Complexity : 0(N + D) N for lists & D for depth of recursive stack
// Space Complexity : 0(N + L) total n elements and l extra lists
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Could only think of recurssion approach

//recussion approach
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> integers = new ArrayList<Integer>();
    private int position = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    //recursive method to flatten list
    private void flattenList(List<NestedInteger> nestedList){
        for(NestedInteger nestedInteger : nestedList){
            if(nestedInteger.isInteger())
                integers.add(nestedInteger.getInteger());
            else
                flattenList(nestedInteger.getList());
        }
    }

    @Override
    public Integer next() {
        if(hasNext())
            return integers.get(position++);
        else
            return null;
    }

    @Override
    public boolean hasNext() {
        return position < integers.size();
    }
}

// Time Complexity : 0(L/N) for makeStackTopAnInteger where L is total no. of lists & N is total no. of integers
//L/N can be treated as ~some integer, so time complexity can be said as 0(1)
// Space Complexity : 0(D) where D is max length of sub list
// Did this code successfully run on Leetcode : Yes

//using 2 deques
public class NestedIterator implements Iterator<Integer> {

    //two deques, one for lists and one for index
    private Deque<List<NestedInteger>> listStack = new ArrayDeque<>();
    private Deque<Integer> indexStack = new ArrayDeque<>();

    //add the list and index onto deques
    public NestedIterator(List<NestedInteger> nestedList) {
        listStack.addFirst(nestedList);
        indexStack.addFirst(0);
    }

    @Override
    public Integer next() {
        if (!hasNext())
            throw new NoSuchElementException();
        int currentPosition = indexStack.removeFirst();
        indexStack.addFirst(currentPosition + 1);
        return listStack.peekFirst().get(currentPosition).getInteger();
    }


    @Override
    public boolean hasNext() {
        makeStackTopAnInteger();
        return !indexStack.isEmpty();
    }


    private void makeStackTopAnInteger() {

        while (!indexStack.isEmpty()) {

            // If the top list is used up, pop it and its index
            if (indexStack.peekFirst() >= listStack.peekFirst().size()) {
                indexStack.removeFirst();
                listStack.removeFirst();
                continue;
            }

            // Otherwise, if it's already an integer, we don't need to do anything
            if (listStack.peekFirst().get(indexStack.peekFirst()).isInteger()) {
                break;
            }

            // Otherwise, it must be a list. We need to update the previous index
            // and then add the new list with an index of 0
            listStack.addFirst(listStack.peekFirst()
                    .get(indexStack.peekFirst()).getList());
            indexStack.addFirst(indexStack.removeFirst() + 1);
            indexStack.addFirst(0);
        }
    }
}