package qnmc.src.test;

import org.junit.jupiter.api.Test;
import qnmc.src.com.qnmc.service.DataProcessingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataProcessingServiceTest {


    @Test
    void testProcessDataThreeWithValidInputZero() {
        // Arrange
        DataProcessingService service = new DataProcessingService();
        String input = "0";

        // Act
        String result = service.processDataThree(input);

        // Assert
        assertEquals("000", result, "Expected binary equivalent of 0 is 000");
    }

    @Test
    void testProcessDataThreeWithValidInputSeven() {
        // Arrange
        DataProcessingService service = new DataProcessingService();
        String input = "7";

        // Act
        String result = service.processDataThree(input);

        // Assert
        assertEquals("111", result, "Expected binary equivalent of 7 is 111");
    }

    @Test
    void testProcessDataThreeWithInvalidInputNegativeNumber() {
        // Arrange
        DataProcessingService service = new DataProcessingService();
        String input = "-1";

        // Act & Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> service.processDataThree(input),
                "Expected ArrayIndexOutOfBoundsException for input -1");
    }

    @Test
    void testProcessDataThreeWithInvalidInputGreaterThanSeven() {
        // Arrange
        DataProcessingService service = new DataProcessingService();
        String input = "8";

        // Act & Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> service.processDataThree(input),
                "Expected ArrayIndexOutOfBoundsException for input 8");
    }

    @Test
    void testProcessDataThreeWithNonNumericInput() {
        // Arrange
        DataProcessingService service = new DataProcessingService();
        String input = "abc";

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> service.processDataThree(input),
                "Expected NumberFormatException for non-numeric input abc");
    }
}