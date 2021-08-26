package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Path fileName = Paths.get(args[0]);
        Path pathToArchive = Paths.get(args[1]);
        Map<String, byte[]> archiveData = new LinkedHashMap<>();

        try (ZipInputStream in = new ZipInputStream(Files.newInputStream(pathToArchive))) {

            ZipEntry zipEntry;

            while ((zipEntry = in.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                copy(in, baos);
                archiveData.put(zipEntry.getName(), baos.toByteArray());
            }
        }

        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(pathToArchive))) {

            ZipEntry zipEntry = new ZipEntry("new/" + fileName.getFileName().toString());
            out.putNextEntry(zipEntry);
            Files.copy(fileName, out);
            out.closeEntry();

            for (Map.Entry<String, byte[]> pair : archiveData.entrySet()) {
                if (!pair.getKey().equals("new/" + fileName.getFileName().toString())) {
                    ZipEntry zEntry = new ZipEntry(pair.getKey());
                    ByteArrayInputStream bais = new ByteArrayInputStream(pair.getValue());
                    out.putNextEntry(zEntry);
                    copy(bais, out);
                    out.closeEntry();
                }
            }
        }
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        int read;
        byte[] buffer = new byte[2048];
        while ((read = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, read);
        }
    }
}
