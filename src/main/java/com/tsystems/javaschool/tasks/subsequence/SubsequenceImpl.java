package com.tsystems.javaschool.tasks.subsequence;

import java.util.Arrays;
import java.util.List;

public class SubsequenceImpl extends Subsequence {
    public boolean find(List subSeq, List seq) {
        if (seq != null && subSeq != null) {
            if (subSeq.size() > seq.size()) {
                System.out.println("Second seq. must be longer than first.");
                return false;
            }
            int i = 0;
            for (int j = 0; j < seq.size(); j++) {
                if (subSeq.get(i).equals(seq.get(j))) {
                    if (i < subSeq.size() - 1) {
                        i++;
                    } else return true;
                }
            }
        } else {
            throw new IllegalArgumentException("One or both sequences have no elements");
        }
        return false;
    }

    public static void main(String[] args) {
        SubsequenceImpl s = new SubsequenceImpl();
        boolean b = s.find(Arrays.asList("A", "B", "C", "D", "H"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "H", "C", "D"));
        System.out.println(b); // Result: true
    }
}
