package com.anz.credit.web;

import com.anz.credit.limit.graph.GraphBuilder;
import com.anz.credit.limit.graph.GraphProcessor;
import com.anz.credit.limit.graph.vo.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditLimitsServiceImpl implements  CreditLimitsService{

    @Autowired
    GraphBuilder graphBuilder;

    @Autowired
    GraphProcessor graphProcessor;

    @Override
    public String getReport(List<List<String>> lines) {
        Graph graph=graphBuilder.buildGraph(lines);
        String report=graphProcessor.processGraph(graph);
        return report;
    }
}
