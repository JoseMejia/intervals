package org.example.tree;

import org.example.model.Interval;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.util.Objects.nonNull;
import static org.example.tree.Node.NIL;
import static org.example.tree.Node.intervalNode;

public class Tree {

    private Node root = NIL;

    public Tree() {
    }

    public void addInterval(final Interval i) {
        root = insert(root, i);
    }

    public List<Interval> collectIntervals() {
        if (root == NIL) {
            return List.of();
        }
        return Stream.concat(Stream.of(root.getValue()), collectChildren(root)).collect(Collectors.toList());
    }


    private Node insert(final Node node, final Interval i) {
        if (node == NIL)
            return intervalNode(i);

        if (i.isBefore(node.getValue()))
            node.setLeft(insert(node.getLeft(), i));
        else if (i.isAfter(node.getValue()))
            node.setRight(insert(node.getRight(), i));
        else {
            Stream<Interval> children = collectChildren(node);
            node.setValue(node.getValue().merge(i));
            node.setLeft(NIL);
            node.setRight(NIL);
            node.setHeight(0);
            children.forEach(interval -> insert(node, interval));
            return node;
        }

        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && i.isBefore(node.getLeft().getValue()))
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && i.isAfter(node.getRight().getValue()))
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && i.isAfter(node.getLeft().getValue())) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && i.isBefore(node.getRight().getValue())) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        //  Update heights
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }


    private int height(final Node node) {
        if (node == NIL)
            return 0;

        return node.getHeight();
    }

    private int getBalance(final Node node) {
        if (node == NIL)
            return 0;

        return height(node.getLeft()) - height(node.getRight());
    }


    private Stream<Interval> collectChildren(Node node) {
        final Stream.Builder<Node> intervals = Stream.builder();
        final Deque<Node> stack = new LinkedList<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            if (node.getValue() != currentNode.getValue()) {
                intervals.add(currentNode);
            }
            Stream.of(currentNode.getLeft(), currentNode.getRight())
                    .filter(it -> it != NIL)
                    .forEach(stack::push);

        }
        return intervals.build().map(Node::getValue);
    }
}
