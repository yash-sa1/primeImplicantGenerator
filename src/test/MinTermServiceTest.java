package qnmc.src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qnmc.src.com.qnmc.model.MinTerm;
import qnmc.src.com.qnmc.service.MinTermService;
import qnmc.src.com.qnmc.utils.ExceptionQuine;

class MinTermServiceTest {

    private MinTermService minTermService;

    @BeforeEach
    void setUp() {
        minTermService = new MinTermService();
    }

    private MinTerm createMinTerm(String term) {
        return new MinTerm(term);
    }

    @Test
    void equalsTo_identicalMinterms() throws ExceptionQuine {
        MinTerm minTerm1 = createMinTerm("101");
        MinTerm minTerm2 = createMinTerm("101");

        assertTrue(minTermService.equalsTo(minTerm1, minTerm2));
    }

    @Test
    void equalsTo_differentMinterms() throws ExceptionQuine {
        MinTerm minTerm1 = createMinTerm("101");
        MinTerm minTerm2 = createMinTerm("111");

        assertFalse(minTermService.equalsTo(minTerm1, minTerm2));
    }

    @Test
    void equalsTo_throwsExceptionForDifferentCount() {
        MinTerm minTerm1 = createMinTerm("101");
        MinTerm minTerm2 = createMinTerm("10");

        ExceptionQuine exception = assertThrows(ExceptionQuine.class,
                () -> minTermService.equalsTo(minTerm1, minTerm2));

        assertTrue(exception.getMessage().contains("MinTermService::equalsTo"));
    }

    @Test
    void countingDifferencesBetweenMinterms_noDifferences() throws ExceptionQuine {
        MinTerm minTerm1 = createMinTerm("101");
        MinTerm minTerm2 = createMinTerm("101");

        assertEquals(0, minTermService.countingDifferencesBetweenMinterms(minTerm1, minTerm2));
    }

    @Test
    void countingDifferencesBetweenMinterms_withDifferences() throws ExceptionQuine {
        MinTerm minTerm1 = createMinTerm("101");
        MinTerm minTerm2 = createMinTerm("110");

        assertEquals(2, minTermService.countingDifferencesBetweenMinterms(minTerm1, minTerm2));
    }
}