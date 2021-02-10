package com.anz.credit.web;

import com.anz.credit.CreditApplication;
import com.anz.credit.limit.graph.GraphBuilder;
import com.anz.credit.limit.graph.GraphProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CreditApplication.class)
class CreditLimitsServiceImplTest {

    @Autowired
    CreditLimitsService creditLimitsService;


    @Before
    public void setup() {
    }

    @Test
    public void whenLinesAreAnzSample_thenReturnAnzReportSample() {
        List<List<String>> lines=getDummyLines();
        String report=creditLimitsService.getReport(lines);
       
        String expextedResult1="Entities: E/F/: </br>  Limit breach at E (limit = 200, direct utilisation = 150, combined utilisation = 230).</br></br>Entities: A/B/C/D/:</br>  No limit breaches </br></br>";
        String expextedResult2="Entities: E/F/: </br>  Limit breach at E (limit = 200, direct utilisation = 150, combined utilisation = 230).</br></br>Entities: A/B/D/C/:</br>  No limit breaches </br></br>";
        String expextedResult3="Entities: A/B/C/D/:</br>  No limit breaches </br></br></br>Entities: E/F/: </br>  Limit breach at E (limit = 200, direct utilisation = 150, combined utilisation = 230).</br>";
        String expextedResult4="Entities: A/B/D/C/:</br>  No limit breaches </br></br></br>Entities: E/F/: </br>  Limit breach at E (limit = 200, direct utilisation = 150, combined utilisation = 230).</br>";

        assertThat(report, anyOf(is(expextedResult1), is(expextedResult2),is(expextedResult3),is(expextedResult4)));
    }

    private List<List<String>> getDummyLines() {
        List<List<String>> lines=new ArrayList<List<String>>();
        List<String> line1= Arrays.asList("A","","100","0");
        List<String> line2= Arrays.asList("B","A","90","10");
        List<String> line3= Arrays.asList("C","B","40","20");
        List<String> line4= Arrays.asList("D","B","40","30");
        List<String> line5= Arrays.asList("E","","200","150");
        List<String> line6= Arrays.asList( "F","E","100","80");
        lines.add(line1); lines.add(line2); lines.add(line3); lines.add(line4); lines.add(line5); lines.add(line6);
        return lines;
    }







    private List<String> getDummyLine1() {
        return null;
    }


}
