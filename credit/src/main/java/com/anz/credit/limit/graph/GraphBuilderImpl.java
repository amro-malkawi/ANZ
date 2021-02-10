package com.anz.credit.limit.graph;

import com.anz.credit.limit.graph.vo.Graph;
import com.anz.credit.limit.graph.vo.Node;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphBuilderImpl implements GraphBuilder{
    @Override
    public Graph buildGraph(List<List<String>> CsvRecords) {
        Graph graph=new Graph();
        for(List<String> currentLine: CsvRecords){
            Node node=getNode(currentLine);
            if(node.isHead())
                graph.addHeadNode(node);
            else
                graph.addNormalNode(node);      // here if head node was not added already u will have exception
        }
        return graph;
    }

    private Node getNode(List<String> currentLine) {
        Node node=new Node();
        node.setId(currentLine.get(0));
        if("".equals(currentLine.get(1))) {
            node.setHead(true);
        }else {
            node.setHead(false);
            node.setHeadId(currentLine.get(1));
        }
        node.setLimit(Integer.parseInt(currentLine.get(2)));
        node.setDirectUtilization(Integer.parseInt(currentLine.get(3)));
        return node;
    }
}
