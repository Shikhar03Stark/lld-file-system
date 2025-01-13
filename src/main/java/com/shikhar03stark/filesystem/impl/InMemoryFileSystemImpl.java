package com.shikhar03stark.filesystem.impl;

import com.shikhar03stark.filesystem.FileSystem;
import com.shikhar03stark.filesystem.entity.FSNode;
import com.shikhar03stark.filesystem.entity.FSPath;
import com.shikhar03stark.filesystem.factory.FSNodeFactory;
import com.shikhar03stark.filesystem.factory.FSPathFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class InMemoryFileSystemImpl implements FileSystem {

    private final FSNode fsRoot;

    public InMemoryFileSystemImpl() {
        this.fsRoot = new FSNode("/", true);
    }

    private FSNode find(final List<String> nodeNames) {
        FSNode fsNode = fsRoot;
        int idx = 0;
        while (Objects.nonNull(fsNode) && idx < nodeNames.size()) {
            fsNode = fsNode.getNext(nodeNames.get(idx));
            idx++;
        }
        return fsNode;
    }

    @Override
    public FSNode create(FSPath fsPath) {
        boolean isDir = fsPath.isDir();

        FSNode existingNode = find(fsPath.getNodeNames());
        if (Objects.nonNull(existingNode)) {
            if (!isDir){
                existingNode.getStat().setData(new byte[0]);
            }
            return existingNode;
        }

        final List<String> nodeNames = fsPath.getNodeNames();
        FSNode fsNode = fsRoot;
        for(int i = 0; i<nodeNames.size()-1; i++) {
            FSNode nextNode = fsNode.getNext(nodeNames.get(i));
            if (Objects.isNull(nextNode)) {
                nextNode = FSNodeFactory.newDir(nodeNames.get(i));
                fsNode.getNextNodes().add(nextNode);
            }
            fsNode = nextNode;
        }

        FSNode createdNode = null;
        if (isDir) {
            createdNode = FSNodeFactory.newDir(nodeNames.getLast());
        } else {
            createdNode = FSNodeFactory.newFile(nodeNames.getLast(), new byte[0]);
        }
        fsNode.getNextNodes().add(createdNode);

        return createdNode;

    }

    @Override
    public void remove(FSPath fsPath) {
        FSNode existingNode = find(fsPath.getNodeNames());
        if (Objects.isNull(existingNode)) return;

        FSNode parentNode = null;
        FSNode node = fsRoot;

        for(String name: fsPath.getNodeNames()) {
            FSNode nextNode = node.getNext(name);
            if (Objects.isNull(nextNode)) return;
            parentNode = node;
            node = nextNode;
        }

        if (Objects.isNull(parentNode)) return;

        parentNode.getNextNodes().remove(node);
    }

    @Override
    public void move(FSPath fromFsPath, FSPath toFsPath) {
        FSNode existingNode = find(fromFsPath.getNodeNames());
        if (Objects.isNull(existingNode)) return;

        if (fromFsPath.isDir() && !toFsPath.isDir()) return;

        // case: from is file, to is file
        if (!fromFsPath.isDir() && !toFsPath.isDir()) {
            FSNode toNode = create(toFsPath);
            toNode.setStat(existingNode.getStat().clone());
            remove(fromFsPath);
        }
        // case from is file, to is dir
        else if (!fromFsPath.isDir() && toFsPath.isDir()) {
            FSPath appendedPath = FSPathFactory.fromAppend(toFsPath, existingNode.getName());
            FSNode toNode = create(appendedPath);
            toNode.setStat(existingNode.getStat().clone());
            remove(fromFsPath);
        }
        // case from is dir, to is dir
        else {
            FSNode toNode = create(toFsPath);
            toNode.getNextNodes().add(existingNode.clone());
            remove(fromFsPath);
        }
    }

    @Override
    public FSNode get(FSPath fsPath) {
        return find(fsPath.getNodeNames());
    }
}
