/*
 * NestedIterator.java
 *
 * Approach:
 * - Eager DFS flattening: recursively traverse the nestedList in the constructor and collect all integers
 *   into an ArrayList. The iterator then reads from this flattened list using an index.
 *
 * Time Complexity:
 * - Constructor: O(n) where n is the total number of integers across the nested structure.
 * - next(): O(1)
 * - hasNext(): O(1)
 *
 * Space Complexity:
 * - O(n) extra space to store the flattened integers.
 *
 * LeetCode:
 * - https://leetcode.com/problems/flatten-nested-list-iterator/ (Problem 341)
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    List<Integer> list = new ArrayList<>();

    int idx = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        helper(nestedList);
    }

    private void helper(List<NestedInteger> nestedList) {

        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                list.add(n.getInteger());
            } else {
                helper(n.getList());
            }
        }
    }

    @Override
    public Integer next() {
        int val = list.get(idx);
        idx++;
        return val;
    }

    @Override
    public boolean hasNext() {
        return idx < list.size();
    }
}
