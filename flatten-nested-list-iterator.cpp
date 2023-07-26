// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

//using stack
#include <iostream>
#include <vector>
#include <stack>

class NestedInteger {
public:
    // Constructor for single integer
    NestedInteger(int value) : integer(true), intValue(value) {}

    // Constructor for nested list
    NestedInteger(std::vector<NestedInteger>& nestedList) : integer(false), nestedListValue(nestedList) {}

    // Returns true if this NestedInteger holds a single integer, rather than a nested list.
    bool isInteger() const {
        return integer;
    }

    // Returns the single integer that this NestedInteger holds, if it holds a single integer.
    // Return 0 if this NestedInteger holds a nested list.
    int getInteger() const {
        return intValue;
    }

    // Returns the nested list that this NestedInteger holds, if it holds a nested list.
    // Return an empty vector if this NestedInteger holds a single integer.
    std::vector<NestedInteger> getList() const {
        return nestedListValue;
    }

private:
    bool integer;
    int intValue;
    std::vector<NestedInteger> nestedListValue;
};

class NestedIterator {
private:
    NestedInteger nextInt;
    std::stack<std::vector<NestedInteger>::iterator> stack_begin;
    std::stack<std::vector<NestedInteger>::iterator> stack_end;

public:
    NestedIterator(std::vector<NestedInteger>& nestedList) {
        stack_begin.push(nestedList.begin());
        stack_end.push(nestedList.end());
    }

    int next() {
        int result = nextInt.getInteger();
        stack_begin.top()++;
        return result;
    }

    bool hasNext() {
        while (!stack_begin.empty()) {
            if (stack_begin.top() == stack_end.top()) {
                stack_begin.pop();
                stack_end.pop();
            } else {
                auto it = stack_begin.top();
                if (it->isInteger()) {
                    nextInt = *it;
                    return true;
                } else {
                    stack_begin.top()++;
                    stack_begin.push(it->getList().begin());
                    stack_end.push(it->getList().end());
                }
            }
        }

        return false;
    }
};