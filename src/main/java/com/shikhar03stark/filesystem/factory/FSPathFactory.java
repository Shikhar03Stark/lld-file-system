package com.shikhar03stark.filesystem.factory;

import com.shikhar03stark.filesystem.entity.FSPath;

public class FSPathFactory {
    public static FSPath fromLiteral(String path) {
        return new FSPath(path);
    }

    public static FSPath fromAppend(FSPath fsPath, String append) {
        if (!fsPath.isDir()) return null;
        String rawPath = fsPath + append;
        return fromLiteral(rawPath);
    }
}
