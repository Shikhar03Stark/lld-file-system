package com.shikhar03stark.filesystem;

import com.shikhar03stark.filesystem.entity.FSType;
import com.shikhar03stark.filesystem.impl.InMemoryFileSystemImpl;

import java.util.HashMap;
import java.util.Map;

public class FileSystemAccessor {
    private static final Map<FSType, FileSystem> fsImplMap = new HashMap<>();

    public static FileSystem getInstance(FSType fsType) {
        if (!fsImplMap.containsKey(fsType)) {
            fsImplMap.put(fsType, createInstanceOf(fsType));
        }
        return fsImplMap.get(fsType);
    }

    private static FileSystem createInstanceOf(FSType fsType) {
        return switch (fsType) {
            case IN_MEMORY -> new InMemoryFileSystemImpl();
        };
    }
}
