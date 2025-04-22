package com.repsy.model;

import lombok.Data;
import java.util.List;

@Data
public class MetaData {
    private String name;
    private String version;
    private String author;
    private List<Dependency> dependencies;

    @Data
    public static class Dependency {
        private String packageName;
        private String version;
    }
}

