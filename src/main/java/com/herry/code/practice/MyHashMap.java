package com.herry.code.practice;

import java.util.Arrays;

/**
 * @author herry
 */
public class MyHashMap<K, V> {
    private Entry<K, V>[] table; //哈希表
    private int size; // 有效数据个数
    private static final float DEFAULT_LOAD_FACTOR = 0.75f; //负载因子设定
    private static final int DEFAULT_CAPACITY = 6; //默认容量
    
    // 节点
    public static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;
 
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public MyHashMap() {
        this.table = (Entry<K, V>[])new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyHashMap(int capacity) {
        this.table = (Entry<K, V>[])new Entry[capacity];
        this.size = capacity;
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() % table.length;
    }

    private boolean loadFactor() {
        return size * 1.0 / table.length >= DEFAULT_LOAD_FACTOR;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value);
    }

    private V putVal(int hash, K key, V value) {
        // 通过 hash 值, 找到对应位置
        Entry<K, V> cur = table[hash];
        Entry<K, V> prev = null;
        if (cur == null) {
            table[hash] = new Entry<>(key, value);
        }  else {
            while (cur != null) {
                // 碰到相同值的情况
                if (cur.key.equals(key)) {
                    cur.value = value; //更新下value值
                    return value;
                }
                prev = cur;
                cur = cur.next;
            }
            // prev 后面插入节点
            prev.next = new Entry<>(key, value);
        }
        size++;
        // 判断是否超过了阈值考虑扩容问题。
        if (loadFactor()) {
            resize();
        }
        return value;
    }

    private void resize() {
        // 重新扩容势必会考虑到一个问题, 重新 hash 的问题
        // 即现在表中的元素, 要通过新的 hash 值, 放入扩容后新的位置上
        // 二倍式扩容
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[])new Entry[table.length * 2];
        // 将 oldTable 的数据通过新的 hash, 拷贝进 table 中
        copyData(oldTable);
    }

    private void copyData(Entry<K, V>[] oldTable) {
        // 遍历这个 oldTable 将数据拷贝进他 table 中
        for (int i = 0; i < oldTable.length; i++) {
            Entry<K, V> node = oldTable[i];
            while (node != null) {
                // 不能直接将 node 插入进去, 因为 node.next 后面可能还有其他元素
                // 所以我们要拷贝一份新的 node 进行插入
                Entry<K, V> insertNode = new Entry<>(node.key, node.value);

                int index = hash(node.key); // node要被hash到的新位置
                Entry<K, V> cur = table[index];
                // table当前位置没有元素, 直接插入该节点作为链表的头节点
                if (cur == null) {
                    table[index] = insertNode;
                } else {
                    Entry<K, V> prev = null;
                    // 如果当前数组下标已经有元素了, 就遍历数组中链表往后找
                    while (cur != null) {
                        prev = cur;
                        cur = cur.next;
                    }
                    prev.next = insertNode;
                }
                // 插入 oldTable[i] 当前链表中节点后, node 往后走, 判断还有没有节点需要重新 hash 插入
                node = node.next;
            }
        }
    }

    public V get(K key) {
        // 通过 hash 获取当前 key 所在的位置
        int index = hash(key);
        // 通过 index 位置找到 key 对应的 value
        Entry<K, V> cur = table[index];
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "table=" + Arrays.toString(table) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);
        map.put(4, 44);
        System.out.println("扩容前: ");
        System.out.println(map);
        map.put(5, 55);
        System.out.println("扩容后: ");
        System.out.println(map);
    }

}