package com.shikhar03stark.filesystem;

import com.shikhar03stark.filesystem.entity.FSNode;
import com.shikhar03stark.filesystem.entity.FSPath;

public interface FileSystem {
    FSNode create(FSPath fsPath);
    void remove(FSPath fsPath);
    void move(FSPath fromFsPath, FSPath toFsPath);
    FSNode get(FSPath fsPath);
}
