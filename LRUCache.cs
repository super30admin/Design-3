using System;
using System.Collections.Generic;
using System.Text;

namespace Design
{
    public class LRUCache
    {
        public class ListNode
        {
            public int key;
            public int value;
            public ListNode next;
            public ListNode prev;
            public ListNode(int key, int value)
            {
                this.key = key;
                this.value = value;
            }
        }

        ListNode head, tail;
        int capacity;
        Dictionary<int, ListNode> hashset;

        private void RemoveNode(ListNode node)
        {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void AddToHead(ListNode node)
        {
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }

        public LRUCache(int capacity)
        {
            hashset = new Dictionary<int, ListNode>();
            this.capacity = capacity;
            head = new ListNode(-1, -1);
            tail = new ListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public int Get(int key)
        {
            if (hashset.ContainsKey(key))
            {
                ListNode node = hashset[key];
                RemoveNode(node);
                AddToHead(node);
                return node.value;
            }
            return -1;
        }

        public void Put(int key, int value)
        {

            if (hashset.ContainsKey(key))
            {
                ListNode node = hashset[key];
                RemoveNode(node);
                AddToHead(node);
                node.value = value;
            }
            else
            {

                if (hashset.Count == capacity)
                {
                    ListNode tailNode = tail.prev;
                    RemoveNode(tailNode);
                    hashset.Remove(tailNode.key);
                }

                ListNode newNode = new ListNode(key, value);
                hashset.Add(key, newNode);
                AddToHead(newNode);
            }
        }
    }
}
