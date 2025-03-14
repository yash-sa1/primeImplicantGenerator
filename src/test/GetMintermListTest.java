package qnmc.src.test;

import org.junit.jupiter.api.Test;
import qnmc.src.com.qnmc.service.GetMintermList;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetMintermListTest {

    @Test
    void testGetMin_EmptySet() {
        GetMintermList getMintermList = new GetMintermList();


        Set<String> result = getMintermList.getMin();

        assertTrue(result.isEmpty(), "The returned set should be empty when no elements have been added.");
    }

    @Test
    void testGetMin_SingleElement() {
        GetMintermList getMintermList = new GetMintermList();
        String element = "101";
        getMintermList.setMinList(element);

        Set<String> result = GetMintermList.getMin();

        assertEquals(1, result.size(), "The set should contain exactly one element.");
        assertTrue(result.contains(element), "The set should contain the added element.");
    }

    @Test
    void testGetMin_MultipleElements() {
        // Arrange
        GetMintermList getMintermList = new GetMintermList();
        String element1 = "111";
        String element2 = "101";
        getMintermList.setMinList(element1);
        getMintermList.setMinList(element2);

        Set<String> result = GetMintermList.getMin();

        assertEquals(2, result.size(), "The set should contain exactly two elements.");
        assertTrue(result.contains(element1), "The set should contain the first added element.");
        assertTrue(result.contains(element2), "The set should contain the second added element.");
    }

    @Test
    void testGetMin_ClearSet() {
        GetMintermList getMintermList = new GetMintermList();
        getMintermList.setMinList("101");
        getMintermList.setMinList("111");

        GetMintermList.getMin().clear();

        Set<String> result = GetMintermList.getMin();

        assertTrue(result.isEmpty(), "The set should be empty after clearing all elements.");
    }

}