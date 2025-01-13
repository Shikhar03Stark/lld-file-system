package com.shikhar03stark;

import com.shikhar03stark.filesystem.FileSystem;
import com.shikhar03stark.filesystem.FileSystemAccessor;
import com.shikhar03stark.filesystem.entity.FSNode;
import com.shikhar03stark.filesystem.entity.FSType;
import com.shikhar03stark.filesystem.factory.FSPathFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = FileSystemAccessor.getInstance(FSType.IN_MEMORY);
        fs.create(FSPathFactory.fromLiteral("/c/uber/lld/"));
        fs.create(FSPathFactory.fromLiteral("/c/uber/hld/"));
        fs.create(FSPathFactory.fromLiteral("/d/personal/"));
        fs.create(FSPathFactory.fromLiteral("/c/uber/lld/test.txt"));
        FSNode personalTextFile = fs.create(FSPathFactory.fromLiteral("/d/personal/test.txt"));

        personalTextFile.getStat().setData("Hello Harshit".getBytes(StandardCharsets.UTF_8));

        fs.remove(FSPathFactory.fromLiteral("/c/uber/"));

        fs.move(
                FSPathFactory.fromLiteral("/d/personal/test.txt"),
                FSPathFactory.fromLiteral("/c/uber/practice/")
        );

        FSNode fsNode = fs.get(FSPathFactory.fromLiteral("/c/uber/practice/test.txt"));
        System.out.println(fsNode.getName());
        System.out.println(new String(fsNode.getStat().getData()));
    }
}