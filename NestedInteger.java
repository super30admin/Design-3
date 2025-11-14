/*
 * NestedInteger.java
 *
 * Purpose:
 * - This interface represents either a single integer or a nested list of NestedInteger.
 * - It is the helper interface used by iterator implementations that flatten nested lists.
 *
 * Notes:
 * - This file only declares the API; algorithmic complexity is determined by the
 *   iterator implementations that consume this interface (for example, `NestedIterator` or `NestedIterator2`).
 *
 * LeetCode:
 * - https://leetcode.com/problems/flatten-nested-list-iterator/ (Problem 341)
 */

import java.util.List;

public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
