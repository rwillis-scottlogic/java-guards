package com.rowanwillis.guards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GuardTests {

    static Stream<Arguments> nameValuePairs()
    {
        return Stream.of(
            Arguments.of(100, "SomeInteger"),
            Arguments.of("The quick brown fox jumps over the lazy dog", "MyPhrase"),
            Arguments.of(new Object(), "AnyObject")
        );
    }

    @ParameterizedTest
    @MethodSource("nameValuePairs")
    <TValue> void aNewGuard_hasTheGivenValue(TValue value, String name)
    {
        final var guard = Ensure.that(value, name);

        assertEquals(value, guard.getValue());
    }

    @ParameterizedTest
    @MethodSource("nameValuePairs")
    <TValue> void aNewGuard_hasTheGivenName(TValue value, String name)
    {
        final var guard = Ensure.that(value, name);

        assertEquals(name, guard.getName());
    }

    @ParameterizedTest
    @MethodSource("nameValuePairs")
    <TValue> void aNewGuard_givenNoName_hasTheNameValue(TValue value, String name)
    {
        final var guard = Ensure.that(value);

        assertEquals("Value", guard.getName());
    }

    @Test
    void isNotNull_givenNull_throwsIllegalArgumentException()
    {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
                Ensure.thatString(null).isNotNull());

        assertEquals("Value must not be null.", exception.getMessage());
    }

    @Test
    void isNotNull_givenNotNull_returnsTheGuard()
    {
        final var guard = Ensure.that(new Object());
        final var result = guard.isNotNull();

        assertEquals(guard, result);
    }
}