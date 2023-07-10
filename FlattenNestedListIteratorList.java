import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class FlattenNestedListIteratorList {

    /*
   Not an implementation of iterator properly. This is just for understanding basic implementation of this problem

   Because, iterator is just concerned about the next element and allows dynamism in the rest of the data structure.

   The whole processing is done in a constructor with a different data structure i.e., list.
   */

    public interface NestedInteger {


        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public static class NestedIterator implements Iterator<Integer> {

        public List<Integer> li;
        private int i;

        public NestedIterator(List<NestedInteger> nestedList) {

            this.li = new ArrayList<>();

            flatten(nestedList);

        }

        private void flatten(List<NestedInteger> nestedList) {

            for(NestedInteger el: nestedList) {

                if(el.isInteger()) {

                    li.add(el.getInteger());
                }

                else {

                    flatten(el.getList());
                }
            }
        }

        @Override
        public Integer next() { //O(1)

            Integer curr = li.get(i);

            i++;

            return curr;
        }

        @Override
        public boolean hasNext() { // O(1)

            return i < li.size();
        }
    }

}
