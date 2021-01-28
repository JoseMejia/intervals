package org.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.Interval.interval;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntervalTest {

    @Test
    void validateIntervalConstruction() {
        assertThrows(IllegalArgumentException.class, () -> interval(19, 2));
    }

    @Test
    void shouldOverlapWithItself() {
        final Interval i1 = interval(2, 19);
        assertThat(i1.overlaps(i1)).isTrue();
    }

    @Test
    void shouldOverlapWithInterval() {
        final Interval i1 = interval(2, 19);
        final Interval i2 = interval(14, 23);

        assertThat(i1.overlaps(i2)).isTrue();
    }

    @Test
    void shouldOverlapWithIntervalIsCommutative() {
        final Interval i1 = interval(2, 19);
        final Interval i2 = interval(14, 23);

        assertThat(i2.overlaps(i1)).isTrue();
    }

    @Test
    void shouldNotOverlapWithInterval() {
        final Interval i1 = interval(2, 19);
        final Interval i2 = interval(25, 30);

        assertThat(i1.overlaps(i2)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideIntervalsForIsBefore")
    void shouldDetectIntervalBefore(Interval i1, Interval i2, boolean expected) {
        assertThat(i1.isBefore(i2)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideIntervalsForIsAfter")
    void shouldDetectIntervalAfter(Interval i1, Interval i2, boolean expected) {
        assertThat(i1.isAfter(i2)).isEqualTo(expected);
    }


    @ParameterizedTest
    @MethodSource("provideIntervalsToMerge")
    void shouldMergeIntervals(Interval i1, Interval i2, Interval expectedInterval) {
        assertThat(i1.merge(i2)).isEqualTo(expectedInterval);
    }

    private static Stream<Arguments> provideIntervalsForIsBefore() {
        return of(
                Arguments.of(interval(2, 19), interval(25, 30), true),
                Arguments.of(interval(25, 30), interval(2, 19), false),
                Arguments.of(interval(2, 19), interval(2, 19), false)
        );
    }

    private static Stream<Arguments> provideIntervalsForIsAfter() {
        return of(
                Arguments.of(interval(2, 19), interval(25, 30), false),
                Arguments.of(interval(25, 30), interval(2, 19), true),
                Arguments.of(interval(2, 19), interval(2, 19), false)
        );
    }

    private static Stream<Arguments> provideIntervalsToMerge() {
        return of(
                Arguments.of(interval(2, 19), interval(25, 30), interval(2, 30)),
                Arguments.of(interval(25, 30), interval(2, 19), interval(2, 30)),
                Arguments.of(interval(2, 19), interval(2, 19), interval(2, 19))
        );
    }

}