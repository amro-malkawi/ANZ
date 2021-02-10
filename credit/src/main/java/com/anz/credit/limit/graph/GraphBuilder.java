package com.anz.credit.limit.graph;

import com.anz.credit.limit.graph.vo.Graph;

import java.util.List;
import java.util.Set;

public interface GraphBuilder {
    public Graph buildGraph(List<List<String>> CsvRecords);
}
