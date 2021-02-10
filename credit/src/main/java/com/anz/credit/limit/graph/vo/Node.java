package com.anz.credit.limit.graph.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Node {

    private String id;
    private int directUtilization=0;
    private int combinedUtilization=0;
    private int limit=0;
    private Set<Node> adjacentNodes=new HashSet<Node>();
    private StringBuilder message=new StringBuilder();
    private String headerMessageIntro="Entities: ";
    private String headerMessageNodesList=":";
    private boolean isHead;
    private String headId;

    public void addAdjacent(Node destination) {
        adjacentNodes.add(destination);
    }

    public void addHeaderMessage(String headerMessage) {
        this.headerMessageNodesList="/"+headerMessage+this.headerMessageNodesList;
    }

    public void addMessage(String message) {
        this.message.append(message+"</br>");
    }
}
