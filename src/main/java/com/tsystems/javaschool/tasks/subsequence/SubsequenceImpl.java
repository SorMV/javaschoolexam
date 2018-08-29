package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SubsequenceImpl extends Subsequence {
    public boolean find(List subSeq, List seq) {

            if (subSeq.size() > seq.size()) {
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
        return false;
    }
    
}
