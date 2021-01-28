package org.example.tree;

import org.example.model.Interval;

import java.util.Objects;

public class Node {
    public static Node NIL = new Node(null);

    private Interval value;
    private int height;
    private Node left;
    private Node right;

    public static Node intervalNode(Interval value) {
        return new Node(value);
    }

    private Node(Interval value) {
        this.value = value;
        this.left = NIL;
        this.right = NIL;
        this.height = 0;
    }

    public Interval getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setValue(Interval value) {
        this.value = value;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node that = (Node) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    @Override
    public String toString() {
        return "IntervalNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
