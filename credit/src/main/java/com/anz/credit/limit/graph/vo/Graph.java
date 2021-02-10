package com.anz.credit.limit.graph.vo;

import lombok.Data;

import java.util.*;

@Data
public class Graph {
    private Set<Node> headNodes=new HashSet<Node>();
    private Map<String,Node> cashedNodes=new HashMap<String,Node>();  //should be minimized


    public void addHeadNode(Node node){
        headNodes.add(node);
        cashedNodes.put(node.getId(),node);
    }

    public void addNormalNode(Node node) {
        Node parentNode=cashedNodes.get(node.getHeadId());
        parentNode.addAdjacent(node);
        cashedNodes.put(node.getId(),node);
    }

}
