package com.anz.credit.limit.graph;

import com.anz.credit.limit.graph.vo.Graph;
import com.anz.credit.limit.graph.vo.Node;
import com.anz.credit.limit.graph.vo.Report;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


@Component
public class GraphProcessorImpl implements  GraphProcessor{
    @Override
    public String processGraph(Graph graph) {
        StringBuilder report=new StringBuilder();
        for(Node node: graph.getHeadNodes()) {
            processGraphOfEachHeadNode(node);
            report.append(node.getHeaderMessageIntro()+node.getHeaderMessageNodesList().replaceAll("//","/").substring(1));
            report.append(node.getMessage());
        }
        return report.toString();
    }

    public void processGraphOfEachHeadNode( Node node) {


        boolean anyBreach=false;
        Report currentReport=new Report();
        Set<Node> settledNodes = new HashSet<Node>();
        Deque<Node> stackNodes = new ArrayDeque<Node>();

        stackNodes.push(node);
        while(!stackNodes.isEmpty()) {
            Node top=stackNodes.pop();

            currentReport=process(top, settledNodes, stackNodes);
            System.out.println(currentReport.getHeaderMessage().toString());
            node.addHeaderMessage(currentReport.getHeaderMessage().toString());
            if(currentReport.isBreach()) {
                anyBreach=true;
                node.addMessage(currentReport.getMessage());
            }
        }

        if(!anyBreach)
            node.addMessage("</br>  No limit breaches </br>");

       // System.out.println("anyBreach="+anyBreach);

       // System.out.println("getHeaderMessage="+node.getHeaderMessage());
       // System.out.println("getMessage="+node.getMessage());
   }


    private Report process(Node top, Set<Node> settledNodes, Deque<Node> stackNodes) {
        Report report=new Report();
        report.setBreach(false);
        //System.out.println("callinggggggggggggggggggggg");
        //NodeA top=stackNodes.pop();
        if(top.getAdjacentNodes().size()!=0) {
            if(!isTopSettled(top,settledNodes)) {
                stackNodes.push(top);
                for(Node currentNode:top.getAdjacentNodes()) {
                    stackNodes.push(currentNode);
                }
            }else {
                int totalOfAdjacent=getTotalOfAdjacents(top);
                top.setCombinedUtilization(totalOfAdjacent+top.getDirectUtilization());
               // System.out.println(top.getId()+"  in 1 Else="+top.getCombinedUtilization());
                if(top.getCombinedUtilization()>top.getLimit()) {
                    report.setBreach(true);
                    report.setMessage(Report.getBreachMessage(top));
                }
                settledNodes.add(top);
                report.addHeaderMessage(top.getId());
            }

        }else {
            top.setCombinedUtilization(top.getDirectUtilization());
            if(top.getCombinedUtilization()>top.getLimit()) {
                report.setBreach(true);
                report.setMessage(Report.getBreachMessage(top));
            }
          //  System.out.println(top.getId()+" in 2 Else="+top.getCombinedUtilization());
            settledNodes.add(top);
            report.addHeaderMessage(top.getId());

        }

        return report;

    }


    private  int getTotalOfAdjacents(Node top) {
        int total=0;
        for(Node currentNode:top.getAdjacentNodes()) {
            total=total+currentNode.getCombinedUtilization();
        }
        return total;

    }

    private  boolean isTopSettled(Node top, Set<Node> settledNodes) {
        boolean settled=true;
        for(Node currentNode:top.getAdjacentNodes()) {
            if(settledNodes.contains(currentNode))
                continue;
            settled= false;
            break;
        }
        return settled;
    }
}
