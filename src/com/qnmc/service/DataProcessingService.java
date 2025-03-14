package qnmc.src.com.qnmc.service;

import java.util.Set;

public class DataProcessingService {

    private int k = 0;
    private Set<String> set;

    // Getters and setters for k, set:
    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void clearSet() {
        if (set != null) {
            set.clear();
        }
    }


    public String processDataThree(String input) {


        String bin[] = { "000", "001", "010", "011", "100", "101", "110", "111" };

        int i = Integer.parseInt(input);

        return bin[i];

    }

    public String processDataFour(String input) {

        String bin[] = { "0000", "0001", "0010", "0011", "0100", "0101",
                "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101",
                "1110", "1111" };

        int i = Integer.parseInt(input);

        return bin[i];

    }

    public String processDataFive(String input) {

        String bin[] = { "00000", "00001", "00010", "00011", "00100", "00101",
                "00110", "00111", "01000", "01001", "01010", "01011", "01100",
                "01101", "01110", "01111", "10000", "10001", "10010", "10011",
                "10100", "10101", "10110", "10111", "11000", "11001", "11010",
                "11011", "11100", "11101", "11110", "11111" };

        int i = Integer.parseInt(input);

        return bin[i];

    }

}
