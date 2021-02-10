package com.anz.credit.limit.graph.vo;

import lombok.Data;

@Data
public class Report {
    private String message;
    private String headerMessage="";
    private boolean isBreach;


    public void addHeaderMessage(String headerMessage) {
        this.headerMessage=this.headerMessage+headerMessage;
        //this.headerMessage.append(headerMessage+"/");
    }
    public static String getBreachMessage(Node node) {
        return " </br>  Limit breach at "+node.getId()+" (limit = "+node.getLimit()+", direct utilisation = "+node.getDirectUtilization()+", combined utilisation = "+node.getCombinedUtilization()+").</br>";
    }

}
