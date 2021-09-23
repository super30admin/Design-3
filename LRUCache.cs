using System;
using System.Collections.Generic;
using System.Text;

namespace Design
{
    class LRUCacheLC
    {
        //TC: O(1)
        //SC: O(capacity)
        class LRUCache
        {
            class Node
            {
                internal int key, value;
                internal Node next, prev;
                public Node(int key, int value)
                {
                    this.key = key;
                    this.value = value;
                }
            }
            Node head, tail;
            private void addtoHead(Node node)
            {
                node.next = head.next;
                node.prev = head;
                node.next.prev = node;
                head.next = node;
            }
            private void removeNode(Node node)
            {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            int capacity;
            Dictionary<int, Node> map;
            public LRUCache(int capacity)
            {
                head = new Node(-1, -1);
                tail = new Node(-1, -1);
                head.next = tail;
                tail.prev = head;
                this.capacity = capacity;
                this.map = new Dictionary<int, Node>();
            }

            public int get(int key)
            {
                if (map.ContainsKey(key))
                {
                    Node node = map[key];
                    removeNode(node);
                    addtoHead(node);
                    return node.value;
                }
                return -1;
            }

            public void put(int key, int value)
            {
                if (map.ContainsKey(key))
                {
                    Node node = map[key];
                    node.value = value;
                    removeNode(node);
                    addtoHead(node);
                }
                else
                {
                    if (capacity == map.Count)
                    {
                        Node tailPrev = tail.prev;
                        removeNode(tailPrev);
                        map.Remove(tailPrev.key);
                    }
                    Node newnode = new Node(key, value);
                    addtoHead(newnode);
                    map.Add(key, newnode);
                }
            }

        }
    }
}
