package com.url.shortener.util;

/**
 * Utility class for Base62 encoding/decoding.
 * Used to generate short URL codes from database-generated IDs.
 *
 * <p>Character set: 0-9, a-z, A-Z (62 characters total)</p>
 */
public class Base62Util {

    private Base62Util() {
        // Utility class — no instantiation
    }

    static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Encodes a positive long ID into a Base62 string.
     *
     * @param id the database-generated ID (must be non-null and >= 0)
     * @return Base62-encoded short string
     * @throws IllegalArgumentException if id is null or negative
     */
    public static String encode(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID must be non-null and non-negative");
        }
        if (id == 0) return "0";

        StringBuilder sb = new StringBuilder();
        long value = id;
        while (value > 0) {
            int rem = (int) (value % 62);
            sb.append(BASE62_CHARS.charAt(rem));
            value /= 62;
        }
        return sb.reverse().toString();
    }

    /**
     * Decodes a Base62 string back to a long ID.
     *
     * @param encoded the Base62-encoded string
     * @return decoded long value
     * @throws IllegalArgumentException if the string is null, empty, or contains invalid characters
     */
    public static long decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            throw new IllegalArgumentException("Encoded string must not be null or empty");
        }
        long result = 0;
        for (char c : encoded.toCharArray()) {
            int index = BASE62_CHARS.indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid Base62 character: " + c);
            }
            result = result * 62 + index;
        }
        return result;
    }
}
