package com.segmentfault.deep.in.java.modular;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionRemoveDemoInJava8 {

    public static void main(String[] args) {

        List<Integer> values = new ArrayList(List.of(1, 2, 3));

        for (int i = 0; i < values.size(); i++) {
            Object value = values.get(i);
            values.remove(value);
        }


    }
}
