package com.shikhar03stark.filesystem.factory;

import com.shikhar03stark.filesystem.entity.FSNode;
import com.shikhar03stark.filesystem.entity.FSPath;

public class FSNodeFactory {

    public static FSNode newDrive(String driveName) {
        return new FSNode(driveName, true);
    }

    public static FSNode newDir(String dirName) {
        return new FSNode(dirName, false);
    }

    public static FSNode newFile(String fileName, byte[] data) {
        FSNode fsNode = new FSNode(fileName,false);
        fsNode.getStat().setTerminal(true);
        fsNode.getStat().setData(data);
        return fsNode;
    }

}
