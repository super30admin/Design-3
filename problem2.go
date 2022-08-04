// Time Complexity : O(n)
// Space Complexity : O(n)

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
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

//Queue/List Method
type NestedIterator struct {
    list    []int
    index   int
    nestedList []*NestedInteger
}

func Constructor(nestedList []*NestedInteger) *NestedIterator {
    iterator:= &NestedIterator{
        list: []int{},
        index: 0,
        nestedList: nestedList,
    }
    
    iterator.dfs(nestedList)
    return iterator
}

func (this *NestedIterator) dfs(nestedList []*NestedInteger){
    for _,item := range nestedList{
        if item.IsInteger(){
            this.list = append(this.list,item.GetInteger())
        }else{
            this.dfs(item.GetList())
        }
    }
}

func (this *NestedIterator) Next() int {
    value := this.list[this.index]
    this.index++
    return value
}

func (this *NestedIterator) HasNext() bool {
    return this.index < len(this.list)
}
