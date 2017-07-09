package com.elp.enums;

import java.io.File;

/**
 * Created by NWJ on 2017/7/5.
 */
public enum PathEnum {
    MAP_PATH("src" + File.separator
            + "main" + File.separator
            + "resources" + File.separator
            + "item" + File.separator
            + "mapsave.txt"),;

    private String path;

    PathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
