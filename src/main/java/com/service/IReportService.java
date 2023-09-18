package com.service;

import com.model.Account;
import com.model.Report;

import java.util.List;

public interface IReportService extends ICrudService<Report>{
    List<Report> getAccountReceiverReport(String usernameParam);
}
