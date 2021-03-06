package com.tsystems.javaschool.tasks.subsequence;

import javax.sound.midi.Sequence;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        
        if(x!=null&&y!=null) {
            if (x.isEmpty()) {
                return true;
            } else {
                Subsequence s = new SubsequenceImpl();
                return s.find(x,y);
            }
        } else {
            throw new IllegalArgumentException("One or both sequences have no elements");
        }
    }
}
