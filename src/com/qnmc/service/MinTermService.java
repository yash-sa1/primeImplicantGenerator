// MinTermService.java
package qnmc.src.com.qnmc.service;

import qnmc.src.com.qnmc.model.MinTerm;
import qnmc.src.com.qnmc.utils.ExceptionQuine;

public class MinTermService {

    // Compares two minterms for equality
    public boolean equalsTo(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.getCount() != b.getCount())
            throw ExceptionQuine.getInstance("MinTermService::equalsTo() - Minterms have different counts");
        for (int i = 0; i < a.getCount(); i++) {
            if (a.getTerm()[i] != b.getTerm()[i])
                return false;
        }
        return true;
    }

    // Counts the number of differences between two minterms
    public int countingDifferencesBetweenMinterms(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.getCount() != b.getCount())
            throw ExceptionQuine.getInstance("MinTermService::countingDifferencesBetweenMinterms() - Minterms have different counts");
        int resCount = 0;
        for (int i = 0; i < a.getCount(); i++) {
            if (a.getTerm()[i] != b.getTerm()[i])
                resCount++;
        }
        return resCount;
    }
}