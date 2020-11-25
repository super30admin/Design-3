// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
/*
have the struct contain an array of pointers to nestedInteger, int i, and cur as pointer to nestedIterator
in constructor just set the input list to st

in next if element at i index in array is integer then return that after i++
else return  cur.Next

in hasnext
if i >= len of array return false
if ith element in array is integer return true
if cur is nil then set is to the list at ith index
check whether this cur elemen hasNext and if so return true
else just increment i and set cur to nil and now again call itself
*/
package main

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (this NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (this NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (this *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (this NestedInteger) GetList() []*NestedInteger {}
 */


// **********not runnable here, runs in leet code
type NestedInteger struct {
}

func (*NestedInteger) IsInteger() bool {
	return true
}
func (*NestedInteger) GetInteger() int {
	return 0
}
func (*NestedInteger) GetList() []*NestedInteger {
	return []*NestedInteger{}
}
//*************

type NestedIterator struct {
	st []*NestedInteger
	i int
	cur *NestedIterator
}

func Constructor(nestedList []*NestedInteger) *NestedIterator {
	return &NestedIterator{st: nestedList}
}

func (this *NestedIterator) Next() int {
	if this.st[this.i].IsInteger() {
		val := this.st[this.i].GetInteger()
		this.i++
		return val
	}
	return this.cur.Next()
}

func (this *NestedIterator) HasNext() bool {
	if this.i >= len(this.st) {
		return false
	}
	if this.st[this.i].IsInteger() {
		return true
	}
	if this.cur == nil {
		this.cur = Constructor(this.st[this.i].GetList())
	}
	if this.cur.HasNext() {
		return true
	}
	this.i++
	this.cur = nil
	return this.HasNext()
}
