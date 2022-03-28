package com.java.learn.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * java8 将list转成map
 * @author hzliuzhujie
 * @date 2022-03-28
 **/
public class ArrayList2Map {

    public void array2Map() {
        ArrayList<String> arrays = new ArrayList<>();
        arrays.add("1@1");
        arrays.add("2@2");
        arrays.add("1@3");
        arrays.add("3@4");
        Map<String, List<String>> collect = arrays.stream().map(arr -> arr.split("@")).collect(
            toMap(arr -> arr[0], arr -> Lists.newArrayList(arr[1]), (List<String> oldList, List<String> newList) -> {
                oldList.addAll(newList);
                return oldList;
            }));
        System.out.println(collect);
    }

    public static void main(String[] args) {
        new ArrayList2Map().array2Map();
    }
}
