package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.costing.IPieceworkDAO;
import com.faraya.legioss.core.dao.payroll.log.IPieceworkLogDAO;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.piecework.DailyPieceworkSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by fabrizzio on 1/6/16.
 */
@Service
public class PieceworkSalaryServiceImpl implements IPieceworkSalaryService{

    @Autowired
    IPieceworkDAO pieceworkDAO;

    @Autowired
    IPieceworkLogDAO pieceworkLogDAO;

    public List<DailyPieceworkSalary> computeDailySalary(Employee employee, Period period, PayrollContext context){

        return null;
    }

}
