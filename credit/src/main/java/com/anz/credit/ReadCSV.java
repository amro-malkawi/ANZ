package com.anz.credit;

import com.anz.credit.limit.graph.GraphBuilderImpl;
import com.anz.credit.limit.graph.vo.Graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadCSV {

    public static void main(String[] args) {
        String fileName = "/home/amro/Documents/tool/data.csv";
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
            GraphBuilderImpl graphBuilderImpl=new GraphBuilderImpl();
            Graph graph=graphBuilderImpl.buildGraph(values);
            System.out.println(graph.getHeadNodes());
            values.forEach(value -> System.out.println(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}