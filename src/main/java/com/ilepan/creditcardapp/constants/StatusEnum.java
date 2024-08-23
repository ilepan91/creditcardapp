package com.ilepan.creditcardapp.constants;

/**
 * The <code>StatusEnum</code> enum represents the status of a user.
 * It can either be <code>INACTIVE</code> or <code>ACTIVE</code>.
 */
public enum StatusEnum {
    /**
     * Represents an inactive status with value "0".
     */
    INACTIVE("0"),

    /**
     * Represents an active status with value "1".
     */
    ACTIVE("1");

    /**
     * The value associated with the status.
     */
    private final String statusValue;

    /**
     * Constructs a <code>StatusEnum</code> with the specified value.
     *
     * @param statusValue The value associated with the status.
     */
    StatusEnum(String statusValue) {
        this.statusValue = statusValue;
    }

    /**
     * Gets the value associated with the status.
     *
     * @return The code associated with the status.
     */
    public String getStatusValue() {
        return statusValue;
    }

    /**
     * Returns the <code>StatusEnum</code> corresponding to the specified value.
     *
     * @param value The value associated with the status.
     * @return The <code>StatusEnum</code> corresponding to the specified value.
     * @throws IllegalArgumentException If no status with the specified value is found.
     */
    public static StatusEnum retrieveStatusValue(String value) {
        for (StatusEnum status : values()) {
            if (status.getStatusValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + value);
    }
}
