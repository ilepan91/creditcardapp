package com.ilepan.creditcardapp.validator;

import com.ilepan.creditcardapp.exception.InvalidOibException;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

/**
 * The OibValidator class provides methods to calculate the control digit of
 * an OIB (Osobni Identifikacijski Broj) and to validate if the given OIB is correct.
 * The OIB is an 11-digit personal identification number.
 */
@Component
public class OibValidator {

   final static int CONTROL_DIGIT_10 = 10;
   final static int CONTROL_DIGIT_11 = 11;

    /**
     * Calculates the control digit for a given 10-digit string.
     *
     * @param theNumber A string containing the first 10 digits of the OIB.
     * @return The control digit for the OIB.
     * @throws IllegalArgumentException if the input OIB is not exactly 10 digits long.
     */
    public int calculateControlDigit(String theNumber) {
        if (theNumber == null || theNumber.length() != 10) {
            throw new IllegalArgumentException("Provided number must be exactly 10 digits long.");
        }

        int x = 10;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(theNumber.charAt(i));
            x = (x + digit) % 10;
            if (x == 0) {
                x = 10;
            }
            x = (x * 2) % 11;
        }

        int controlDigit = CONTROL_DIGIT_11 - x;
        if (controlDigit == CONTROL_DIGIT_10) {
            controlDigit = 0;
        } else if (controlDigit == CONTROL_DIGIT_11) {
            controlDigit = 1;
        }

        return controlDigit;
    }

    /**
     * Validates the given OIB (Personal Identification Number).
     * This method checks if the provided OIB is a numeric value and exactly 11 digits long.
     * It then validates the OIB by verifying the control digit.
     * If the OIB is invalid, it throws an {@link InvalidOibException}.
     *
     * @param theOib the OIB to be validated.
     * @throws InvalidOibException if the OIB is not valid.
     */
    public void validateOIB(String theOib) {
        if (theOib == null || !theOib.matches("\\d{11}")) {
            throw new InvalidOibException(
                    "OIB must be a numeric value and exactly 11 digits long.");
        }

        String oibWithoutControlDigit = theOib.substring(0, 10);
        int controlDigit = Character.getNumericValue(theOib.charAt(10));
        if(!(calculateControlDigit(oibWithoutControlDigit) == controlDigit)){
            throw new InvalidOibException("Provided OIB is not valid!");
        }
    }
}
