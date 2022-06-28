package com.rowanwillis.guards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringGuardTests {

    @Test
    void isNotEmpty_givenANonEmptyString_returnsTheGivenGuard() {
        final var guard = Ensure.thatString("A non-empty string");
        final var result = guard.isNotEmpty();

        assertEquals(guard, result);
    }

    @Test
    void isNotEmpty_givenAnEmptyString_throwsIllegalArgumentException() {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString("", "StringArg").isNotEmpty());

        assertEquals("StringArg must not be empty.", exception.getMessage());
    }

    static Stream<Arguments> stringsOfExactLength()
    {
        return Stream.of(
            Arguments.of("", 0),
            Arguments.of("abcdefghijklmnopqrstuvwxyz", 26),
            Arguments.of("The quick brown fox jumps over the lazy dog.", 44)
        );
    }

    static Stream<Arguments> longerStrings()
    {
        return Stream.of(
            Arguments.of("A non-empty string", 0),
            Arguments.of("abcdefghijklmnopqrstuvwxyz", 25),
            Arguments.of("The quick brown fox jumps over the lazy dog.", 10)
        );
    }

    static Stream<Arguments> shorterStrings()
    {
        return Stream.of(
            Arguments.of("", 1000),
            Arguments.of("abcdefghijklmnopqrstuvwxyz", 27),
            Arguments.of("The quick brown fox jumps over the lazy dog.", 50)
        );
    }

    @ParameterizedTest
    @MethodSource("stringsOfExactLength")
    void hasLength_givenTheCorrectLengthString_returnsTheGuard(String value, int requiredLength) {
        final var guard = Ensure.thatString(value);
        final var result = guard.hasLength(requiredLength);

        assertEquals(guard, result);
    }

    @Test
    void hasLength_givenNull_throwsIllegalArgumentException() {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString(null, "StringArg").hasLength(100));

        assertEquals("StringArg must have length 100.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("longerStrings")
    void hasLength_givenALongerString_throwsIllegalArgumentException(String value, int requiredLength) {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString(value, "StringArg").hasLength(requiredLength));

        assertEquals("StringArg must have length " + requiredLength + ".", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("shorterStrings")
    void hasLength_givenAShorterString_throwsIllegalArgumentException(String value, int requiredLength) {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString(value, "StringArg").hasLength(requiredLength));

        assertEquals("StringArg must have length " + requiredLength + ".", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("stringsOfExactLength")
    void hasLengthAtMost_givenTheCorrectLengthString_returnsTheGuard(String value, int requiredLength) {
        final var guard = Ensure.thatString(value);
        final var result = guard.hasLengthAtMost(requiredLength);

        assertEquals(guard, result);
    }

    @Test
    void hasLengthAtMost_givenNull_returnsTheGuard() {
        final var guard = Ensure.thatString(null);
        final var result = guard.hasLengthAtMost(100);

        assertEquals(guard, result);
    }

    @ParameterizedTest
    @MethodSource("shorterStrings")
    void hasLengthAtMost_givenAShorterString_returnsTheGuard(String value, int requiredLength) {
        final var guard = Ensure.thatString(value);
        final var result = guard.hasLengthAtMost(requiredLength);

        assertEquals(guard, result);
    }

    @ParameterizedTest
    @MethodSource("longerStrings")
    void hasLengthAtMost_givenALongerString_throwsIllegalArgumentException(String value, int requiredLength) {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString(value, "StringArg").hasLengthAtMost(requiredLength));

        assertEquals("StringArg must have length at most " + requiredLength + ".", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("stringsOfExactLength")
    void hasLengthAtLeast_givenTheCorrectLengthString_returnsTheGuard(String value, int requiredLength) {
        final var guard = Ensure.thatString(value);
        final var result = guard.hasLengthAtLeast(requiredLength);

        assertEquals(guard, result);
    }

    @ParameterizedTest
    @MethodSource("longerStrings")
    void hasLengthAtMost_givenALongerString_returnsTheGuard(String value, int requiredLength) {
        final var guard = Ensure.thatString(value);
        final var result = guard.hasLengthAtLeast(requiredLength);

        assertEquals(guard, result);
    }

    @Test
    void hasLengthAtLeast_givenNull_throwsIllegalArgumentException() {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString(null, "StringArg").hasLengthAtLeast(1));

        assertEquals("StringArg must have length at least " + 1 + ".", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("shorterStrings")
    void hasLengthAtLeast_givenAShorterString_throwsIllegalArgumentException(String value, int requiredLength) {
        final var exception = assertThrows(IllegalArgumentException.class, () ->
            Ensure.thatString(value, "StringArg").hasLengthAtLeast(requiredLength));

        assertEquals("StringArg must have length at least " + requiredLength + ".", exception.getMessage());
    }
}