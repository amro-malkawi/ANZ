package com.anz.credit.web;

import java.util.List;

public interface CreditLimitsService {
    public String getReport(List<List<String>> lines);
}
