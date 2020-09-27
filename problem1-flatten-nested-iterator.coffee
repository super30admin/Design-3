#https://leetcode.com/problems/flatten-nested-list-iterator/
#// Time Complexity :
#    next(): O(N) could potentially be a nested array to depth N at first element
#    hasNext(): O(1) just checks if stack is empty
#// Space Complexity : O(N) where N is max recursive depth at any 1 element in input
#// Did this code successfully run on Leetcode : no
#    (I printed output and it was correct in leetcode, not sure why it isn't working)
#// Any problem you faced while coding this :
#
#   leetcode was annoying, directions for this problem unclear
#
#// Your code here along with comments explaining your approach
#
# Have a queue
# Push one element from input into the queue
# if element is an integer just push that
# if it is a list, push each individual element
# everytime you next(), return queue front but also
#    grab another element from input
NestedIterator = (nestedList) ->
  ni = Object.assign(
    Object.create(NestedIterator::),
      stack: [],
      nestedList: nestedList,
  )

  ni.pushOne()

  ni

NestedIterator::pushOne = () ->
  element = @nestedList.shift()
  return if !element?

  if !Array.isArray(element)
    @stack.push(element)
  else
    for i in [0...element.length]
      @stack.push(element[i])

#/**
# * @this NestedIterator
# * @returns {boolean}
# */
NestedIterator::hasNext = () ->
  @stack.length > 0

#/**
# * @this NestedIterator
# * @returns {integer}
# */
NestedIterator::next = () ->
  retval = @stack.shift()

  @pushOne() if @stack.length is 0

  retval

#/**
# * Your NestedIterator will be called like this:
# * var i = new NestedIterator(nestedList), a = [];
# * while (i.hasNext()) a.push(i.next());
#*/

mn = NestedIterator([[1,1],2,[1,1]])
while mn.hasNext()
  console.log(mn.next())

i = new NestedIterator([[1,1],2,[1,1]])
a = []
while (i.hasNext())
  a.push(i.next())
a
