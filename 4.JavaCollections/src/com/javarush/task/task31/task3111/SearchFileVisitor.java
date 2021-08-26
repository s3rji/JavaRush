package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    private List<Path> foundFiles = new ArrayList<>();

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        if (hasPartOfName(file) && hasPartOfContent(file) && lessMaxSize(file, content) && moreMinSize(file, content))
            foundFiles.add(file);

        return super.visitFile(file, attrs);
    }

    private boolean moreMinSize(Path file, byte[] content) {
        return minSize == 0 || content.length > minSize;
    }

    private boolean lessMaxSize(Path file, byte[] content) {
        return maxSize == 0 || content.length < maxSize;
    }

    private boolean hasPartOfContent(Path file) throws IOException {
        if (partOfContent != null) {
            boolean isPartOfContent = false; 
            List<String> fileContent = Files.readAllLines(file);
            for (String line : fileContent) {
                if (line.contains(partOfContent)) {
                    isPartOfContent = true;
                    break;
                }
            }
            return isPartOfContent;
        }
        return true;
    }

    private boolean hasPartOfName(Path file) {
        return partOfName == null || file.getFileName().toString().contains(partOfName);
    }
}
