package com.shikhar03stark.filesystem.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FSNode {
    private final String name;
    private Stat stat;
    private final List<FSNode> nextNodes;
    private final boolean isDrive;

    public FSNode(String name) {
        this.name = name;
        this.nextNodes = new LinkedList<>();
        this.stat = new Stat();
        this.isDrive = false;
    }

    public FSNode(String name, boolean isDrive) {
        this.name = name;
        this.nextNodes = new LinkedList<>();
        this.stat = new Stat();
        this.isDrive = isDrive;
    }

    public String getName() {
        return name;
    }

    public Stat getStat() {
        return stat;
    }

    public List<FSNode> getNextNodes() {
        return nextNodes;
    }

    public boolean isDrive() {
        return isDrive;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    // clone
    @Override
    public FSNode clone() {
        FSNode clonedNode = new FSNode(this.name, this.isDrive);
        clonedNode.setStat(stat);
        nextNodes.forEach(nextNode -> clonedNode.getNextNodes().add(nextNode.clone()));
        return clonedNode;
    }

    public FSNode getNext(String nodeName) {
        return nextNodes
                .stream()
                .filter(next -> next.getName().equals(nodeName))
                .findAny()
                .orElse(null);
    }
}
