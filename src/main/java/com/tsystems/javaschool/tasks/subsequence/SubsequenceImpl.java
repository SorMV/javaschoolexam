package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsequenceImpl extends Subsequence {
    public boolean find(List subSeq, List seq) {
        if (seq != null && subSeq != null) {
            if(subSeq.size()==0) {
                return true;
            }
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
        List x = new ArrayList();
        List y = new ArrayList();
        boolean b = s.find(x,y);
        System.out.println(b); // Result: true
    }
}
