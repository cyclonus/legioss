package com.faraya.legioss.core.payroll;

import com.faraya.legioss.BaseIntegrationTest;
import com.faraya.legioss.core.dao.payroll.log.IAttendanceLogDAO;
import com.faraya.legioss.core.dao.payroll.log.IPieceworkLogDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */

public class PayrollCalculationIT extends BaseIntegrationTest{

    @Autowired
    IAttendanceLogDAO attendanceLogDAO;

    @Autowired
    IPieceworkLogDAO pieceworkLogDAO;

    @Test
    public void testPayrollEmployee(){

    }


}
