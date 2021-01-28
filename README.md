# Interval Challenge

This project implements a merge function in order to merge a series of input intervals [a, b] where
a <= b.

For example:

        merge({[25,30], [2,19], [14, 23], [4,8]}) = {[2,23] [25,30]}

## Requirements:

* Java 11
* An internet connection in order to download the maven wrapper and dependencies

## Launch instructions:

* Execute the file launch.sh 
    chmod +x launch.sh
    ./launch.sh

* Introduce the amount of intervals and then the interval starts and ends.

## Questions


### Wie ist die Laufzeit Ihres Programms ?
The time complexity of this program is O(n log n), where n is the amount of intervals and log is 2-based.
In the best case, the time complexity could be O(1) when all the intervals merge only into one.
    
### Wie kann die Robustheit sichergestellt werden, vor allem auch mit Hinblick auf sehr große Eingaben ?
The function has a single parameter of type Stream<Interval> so that its values can be supplied lazily.  The input
is not required to be sorted.  And the balanced tree is updated by considering one interval at a time.

### Wie verhält sich der Speicherverbrauch ihres Programms ?
In case of no merged intervals, the balanced tree will consume at most n nodes, where n is the amount of intervals.
In the best case where only one interval remains, it will consume 1 node.


    


