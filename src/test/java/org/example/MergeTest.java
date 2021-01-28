package org.example;

import org.example.model.Interval;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Merger.merge;
import static org.example.model.Interval.interval;

class MergeTest {

    @Test
    void shouldMergeIntervals() {
        Stream<Interval> intervalStream = of(interval(25, 30), interval(2, 19), interval(14, 23), interval(4, 8));

        List<Interval> mergedIntervals = merge(intervalStream);

        assertThat(mergedIntervals).containsExactlyInAnyOrder(interval(2, 23), interval(25, 30));
    }

}