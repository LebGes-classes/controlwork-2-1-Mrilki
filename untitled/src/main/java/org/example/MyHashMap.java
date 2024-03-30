package org.example;

public class MyHashMap<K, V> {
    private double loadFactor;
    private int capacity;
    private int size;
    private Node<K, V>[] table;

    public static class Node<K, V>{
            K key;
            V value;
            Node<K, V> next;
            int hash;

            Node(K key, V value, Node<K, V> next) {
                this.key = key;
                this.value = value;
                this.next = next;
                this.hash = key.hashCode();
            }
    }
    public MyHashMap() {
        this.capacity = 16;
        this.loadFactor = 0.75;
        this.table = new Node[16];
    }
    public int createIndex(K key){
        return key.hashCode() % capacity;
    }
    private void resize(){
        int tempCapacity = capacity * 2;
        Node<K, V>[] tempTable = new Node[tempCapacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                Node<K, V> next = node.next;
                int index = createIndex(node.key);
                node.next = tempTable[index];
                tempTable[index] = node;
                node = next;
            }
        }
        table = tempTable;
        capacity = tempCapacity;
    }
    public void add(K key, V value) {

        int index = createIndex(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        Node<K, V> newNode = new Node<>(key, value, table[index]);
        table[index] = newNode;
        size++;
        if (size >= capacity * loadFactor) {
            resize();
        }

    }
    public V get(K key) {
        int index = createIndex(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
    public void remove(K key) {
        int index = createIndex(key);
        Node<K, V> node = table[index];
        Node<K, V> prevNode = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prevNode == null) {
                    table[index] = node.next;
                } else {
                    prevNode.next = node.next;
                }
                size--;
                return;
            }
            prevNode = node;
            node = node.next;
        }

    }
    public int getCapacity(){
        return capacity;
    }

}
