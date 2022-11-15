import java.util.*;

class Problem1 {
    public class NestedInteger {
        // created this class just to avoid conficts on github
        public boolean isInteger() {
            return true;
        }

        public Integer getInteger() {
            return 0;
        }

        public List<NestedInteger> getList() {
            return new ArrayList<>();
        }
    }

    public class NestedIterator implements Iterator<Integer> {
        public List<Integer> list;
        int index = 0;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = new ArrayList<>();
            createList(nestedList);
        }

        public void createList(List<NestedInteger> nestedList) {
            for (NestedInteger x : nestedList) {
                if (x.getInteger() != null) {
                    list.add(x.getInteger());
                } else {
                    createList(x.getList());
                }
            }
        }

        public Integer next() {
            return list.get(index++);
        }

        public boolean hasNext() {
            if (list.size() == 0 || index == list.size()) {
                return false;
            }
            return true;
        }
    }
}