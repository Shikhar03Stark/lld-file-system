package com.shikhar03stark.filesystem.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FSPath {
    private final String rawPath;

    public FSPath(String rawPath) {
        this.rawPath = rawPath;
    }

    public boolean isDir() {
        if (Objects.isNull(rawPath)) return false;
        return rawPath.charAt(rawPath.length() - 1) == '/';
    }

    public List<String> getNodeNames() {
        if (Objects.isNull(rawPath)) return new ArrayList<>();
        return Arrays.stream(rawPath.split("/")).filter(str -> !str.isEmpty()).toList();
    }

    @Override
    public String toString() {
        return rawPath;
    }
}
