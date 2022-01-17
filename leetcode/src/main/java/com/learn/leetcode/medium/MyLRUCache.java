package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题：LRU Cache有一定的容量, 当小于这个容量时, 可以往cache中正常添加数据.
 *
 * 如果添加之后会超过容量, 那么需要把最近不被访问的元素淘汰掉. 也就是说LRU Cache中应该保持的是最近被使用的元素.
 * 
 * 请尝试实现一个简单版本的LRU Cache, 满足上面的条件.
 * 
 * @author hzliuzhujie
 * @date 2022-01-08
 **/
public class MyLRUCache {

    private List<Node> list = new ArrayList<>();

    public List<Node> getList() {
        return list;
    }

    // 容量
    private int c;

    // 缓存
    private Map<Integer, Node> cache = new HashMap<>();

    static class Node {
        private Integer key;
        private Integer data;
        // 位置
        private int pos;

        Node(int key, int data, int pos) {
            this.key = key;
            this.data = data;
            this.pos = pos;
        }

        public Integer getKey() {
            return key;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getPos() {
            return pos;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + ", pos=" + pos + '}';
        }
    }

    public MyLRUCache(int c) {
        this.c = c;
    }

    // 得优化下成链表，移动太多
    public synchronized Integer get(int k) {
        Node exist = cache.get(k);
        if (exist != null) {
            putListToLast(exist.getPos());
            return exist.data;
        }
        return null;
    }

    private void putListToLast(int pos) {
        Node exist = list.get(pos);
        int i = pos + 1;
        for (; i < list.size(); i++) {
            Node tmp = list.get(i);
            tmp.setPos(i - 1);
            list.set(i - 1, tmp);
        }
        exist.setPos(i - 1);
        list.set(i - 1, exist);
    }

    public synchronized void put(int k, int v) {
        Node node = new Node(k, v, list.size());
        if (list.size() < c) {
            list.add(node);
        } else {
            removeFirstList();
            node.setPos(c - 1);
            list.add(node);
        }
        cache.put(k, node);
    }

    private void removeFirstList() {
        Node exist = list.remove(0);
        for (Node node : list) {
            node.setPos(node.pos - 1);
        }
        cache.remove(exist.getKey());
    }

}
