/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.Arrays;
import java.util.List;

/**
 * All rights reserved The source code is protected to its owner
 *
 * @author Abed
 */
public class PlcFacade {

    public void convert(String path) throws IOException {
        Path p = Paths.get(path);
        String phraseFileName = handleName(p.getFileName().toString(), "phrase");
        String motFileName = handleName(p.getFileName().toString(), "mot");
        File phraseFile = new File(p.getParent().toString(), phraseFileName);
        File motFile = new File(p.getParent().toString(), motFileName);
        List<String> lines = Files.readAllLines(p);
        lines.stream().forEach((line) -> {
//            saveBytes(file, "phrase(\""+line+"\").");
            saveLine(phraseFile, "phrase(\"" + line + "\").");
            List<String> words = splitToWords(line);
            words.stream().forEach((word)-> {
                saveLine(motFile, "mot(\"" + word + "\").");
                
            });

        });
    }

    public List<String> splitToWords(String text) {
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            System.out.println("-" + words[i] + "-");
            words[i] = words[i].replaceAll("[^\\w]", "");
            System.out.println(words[i]);
        }
        
        return Arrays.asList(words);

    }

    public String handleName(String name, String prefix) {
        int index = name.lastIndexOf(".");
        String ext = name.substring(index);
        return prefix + "_" + name.replace(ext, ".pl");
    }

    public void saveBytes(File file, String textToSave) {
        try {
            Files.write(file.toPath(), textToSave.getBytes());
        } catch (IOException e) {
        }
    }

    public void saveLine(File file, String textToSave) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.newLine();
            out.write(textToSave);
            out.close();
        } catch (IOException e) {
        }
    }
}
