package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

public class Solution {

    public static void main(String[] args) {
        if (args.length != 2) return;

        File pathSource = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        if (FileUtils.isExist(allFilesContent)) {
            FileUtils.deleteFile(allFilesContent);
        }
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        List<File> files = new ArrayList<>();
        getAllFiles(pathSource, files);

        files.sort(Comparator.comparing(File::getName));

        try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)) {
            for (File file : files) {
                byte[] buf = Files.readAllBytes(file.toPath());
                fileOutputStream.write(buf);
                fileOutputStream.write("\n".getBytes());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAllFiles(File pathSource, List<File> files) {
        File[] allFiles = pathSource.listFiles();
        for (File file : allFiles) {
            if (file.isFile() && file.length() <= 50) files.add(file);
            if (file.isDirectory()) getAllFiles(file, files);
        }
    }
}

