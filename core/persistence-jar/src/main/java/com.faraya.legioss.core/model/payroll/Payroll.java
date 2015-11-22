package com.faraya.legioss.core.model.payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by fabrizzio on 11/19/15.
 */
//Immutable
public class Payroll {

    LocalDate from;

    LocalDate to;

    List<EmployeePayment> payments;

    Map<String,String> taskInfo;
}
