package com.url.shortener.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Base62Util — encode/decode tests")
class Base62UtilTest {

    // ───────────────────────── encode ─────────────────────────

    @Test
    @DisplayName("encode(0) should return '0'")
    void encode_zero_returnsZero() {
        assertEquals("0", Base62Util.encode(0L));
    }

    @Test
    @DisplayName("encode(1) should return '1'")
    void encode_one_returnsOne() {
        assertEquals("1", Base62Util.encode(1L));
    }

    @Test
    @DisplayName("encode(61) should return last Base62 char 'Z'")
    void encode_61_returnsZ() {
        // 61 is the last index → 'Z'
        assertEquals("Z", Base62Util.encode(61L));
    }

    @Test
    @DisplayName("encode(62) should return '10' (carry over)")
    void encode_62_returns10() {
        assertEquals("10", Base62Util.encode(62L));
    }

    @ParameterizedTest(name = "encode({0}) == \"{1}\"")
    @CsvSource({
        "1,       1",
        "10,      a",
        "35,      z",
        "36,      A",
        "61,      Z",
        "62,      10",
        "12345,   3d7",
        "999999,  4c91"
    })
    @DisplayName("Parameterised encode checks")
    void encode_parameterised(long id, String expected) {
        assertEquals(expected.trim(), Base62Util.encode(id));
    }

    @Test
    @DisplayName("encode(null) should throw IllegalArgumentException")
    void encode_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Util.encode(null));
    }

    @Test
    @DisplayName("encode(-1) should throw IllegalArgumentException")
    void encode_negative_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Util.encode(-1L));
    }

    // ───────────────────────── decode ─────────────────────────

    @Test
    @DisplayName("decode('0') should return 0")
    void decode_zero_returns0() {
        assertEquals(0L, Base62Util.decode("0"));
    }

    @Test
    @DisplayName("decode('Z') should return 61")
    void decode_Z_returns61() {
        assertEquals(61L, Base62Util.decode("Z"));
    }

    @Test
    @DisplayName("decode('10') should return 62")
    void decode_10_returns62() {
        assertEquals(62L, Base62Util.decode("10"));
    }

    @Test
    @DisplayName("decode(null) should throw IllegalArgumentException")
    void decode_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Util.decode(null));
    }

    @Test
    @DisplayName("decode('') should throw IllegalArgumentException")
    void decode_empty_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Util.decode(""));
    }

    @Test
    @DisplayName("decode with invalid character should throw IllegalArgumentException")
    void decode_invalidChar_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Util.decode("ab!cd"));
    }

    // ───────────────────── round-trip ─────────────────────────

    @ParameterizedTest(name = "round-trip encode→decode for id={0}")
    @CsvSource({"1", "62", "1000", "99999", "1234567"})
    @DisplayName("encode then decode should return the original ID")
    void roundTrip_encodeDecodeReturnsOriginalId(long id) {
        String encoded = Base62Util.encode(id);
        long decoded  = Base62Util.decode(encoded);
        assertEquals(id, decoded,
                "Round-trip failed for id=" + id + " (encoded='" + encoded + "')");
    }
}
