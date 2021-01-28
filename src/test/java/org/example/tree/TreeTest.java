package org.example.tree;

import org.example.model.Interval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.Interval.interval;

class TreeTest {

    private Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree();
    }


    @Test
    void anEmptyTreeContainsNoIntervals() {
        assertThat(tree.collectIntervals()).isEmpty();
    }

    @Test
    void shouldPopulateIntervalWithRootNode() {
        final Interval i = interval(1, 2);
        tree.addInterval(i);

        assertThat(tree.collectIntervals()).containsExactlyInAnyOrder(i);
    }

    @ParameterizedTest
    @MethodSource("provideIntervals")
    void shouldMergeIntervals(final String description, final Stream<Interval> intervalStream,
                              final List<Interval> expectedIntervals) {

        intervalStream.forEach(tree::addInterval);

        assertThat(tree.collectIntervals()).containsExactlyInAnyOrderElementsOf(expectedIntervals);

    }

    private static Stream<Arguments> provideIntervals() {
        return of(
                Arguments.of("no merge", Stream.of(interval(1, 2), interval(7, 8), interval(3, 4)), List.of(interval(1, 2), interval(3, 4), interval(7, 8))),
                Arguments.of("no merge, sorted input", Stream.of(interval(1, 2), interval(3, 4), interval(7, 8)), List.of(interval(1, 2), interval(3, 4), interval(7, 8))),
                Arguments.of("merge root 1", Stream.of(interval(1, 1), interval(2, 2), interval(1, 2)), List.of(interval(1, 2))),
                Arguments.of("merge root 2", Stream.of(interval(25, 30), interval(2, 19), interval(1, 25), interval(14, 23), interval(4, 8)), List.of(interval(1, 30))),
                Arguments.of("merge left", Stream.of(interval(1, 2), interval(5, 8), interval(10, 11), interval(2, 4)), List.of(interval(1, 4), interval(5, 8), interval(10, 11))),
                Arguments.of("merge right", Stream.of(interval(1, 2), interval(3, 4), interval(5, 6), interval(8, 10), interval(6, 9)), List.of(interval(1, 2), interval(3, 4), interval(5, 10))),
                Arguments.of("negative values", Stream.of(interval(25, 30), interval(2, 19), interval(-11, -4), interval(14, 23), interval(4, 8), interval(1, 25)),
                        List.of(interval(1, 30), interval(-11, -4)))

        );
    }

}