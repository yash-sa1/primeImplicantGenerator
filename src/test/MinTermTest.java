package qnmc.src.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import qnmc.src.com.qnmc.model.MinTerm;
import qnmc.src.com.qnmc.utils.ExceptionQuine;

class MinTermTest {

    @Test
    void testConstructor_validInput() {
        MinTerm minTerm = new MinTerm("101"); // Valid binary string
        assertEquals(3, minTerm.getCount());
        assertArrayEquals(new int[]{1, 0, 1}, minTerm.getTerm());
    }

    @Test
    void testGetCount() {
        MinTerm minTerm = new MinTerm("10101");
        assertEquals(5, minTerm.getCount());
    }

    @Test
    void testGetTerm() {
        MinTerm minTerm = new MinTerm("110");
        assertArrayEquals(new int[]{1, 1, 0}, minTerm.getTerm());
    }

    @Test
    void testMergeMinterms_differsInOnePosition() throws ExceptionQuine {
        MinTerm minTerm1 = new MinTerm("101");
        MinTerm minTerm2 = new MinTerm("111");
        MinTerm merged = MinTerm.mergeMinterms(minTerm1, minTerm2);
        assertEquals("1_1", merged.toString());
    }

    @Test
    void testMergeMinterms_identicalMinterms() throws ExceptionQuine, ExceptionQuine {
        MinTerm minTerm1 = new MinTerm("101");
        MinTerm minTerm2 = new MinTerm("101");
        MinTerm merged = MinTerm.mergeMinterms(minTerm1, minTerm2);
        assertEquals("101", merged.toString());
    }

    @Test
    void testMergeMinterms_throwsExceptionForDifferentLength() {
        MinTerm minTerm1 = new MinTerm("101");
        MinTerm minTerm2 = new MinTerm("10101");
        assertThrows(ExceptionQuine.class, () -> MinTerm.mergeMinterms(minTerm1, minTerm2));
    }
}