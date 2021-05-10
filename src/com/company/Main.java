package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    public static final String REGEX = "[!\\.,\\s-\"’'\\d]";

    public static void main(String[] args) throws IOException {

        createPutDelete();

        Map<String, Integer> map = new HashMap<>();

        final Path path = Paths.get("Story.txt");

        final List<String> lines = Files.readAllLines(path);

        lines.stream()
                .map(str ->str.replaceAll(REGEX, "").toLowerCase())
                .forEach(str -> {
                            IntStream.range(0, str.length()).forEach(index -> {
                                final String symbol = String.valueOf(str.charAt(index));

                                int count = 1;

                                if (map.containsKey(symbol)) {
                                    count = map.get(symbol);
                                    count += 1;
                                }

                                map.put(symbol, count);
                            });
                        }
                );

        class LettersFrequency {
            private int count;
            private String word;

            public LettersFrequency(Map.Entry<String, Integer> entry) {
                count = entry.getValue();
                word = entry.getKey();
            }

            public int getCount() {
                return count;
            }

            @Override
            public String toString() {
                return "count=" + count +
                        ", word='" + word + '\'';
            }
        }

        Comparator<LettersFrequency> comparator = (fe1, fe2) -> Integer.compare(fe1.count, fe2.getCount());

        map.entrySet()
                .stream()
                .map(LettersFrequency::new)
                .sorted(comparator.reversed())
                .forEach(System.out::println);

    }

    static void createPutDelete() {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(0);
        list.remove(0);
        list.remove(list.size() - 1);
        System.out.println(list);
    }
}
