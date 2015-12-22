package com.faraya.legioss.core.payroll;

import com.faraya.legioss.TransactionalSpringJUnit4RunnerTest;
import com.faraya.legioss.core.dao.common.IBusinessDAO;
import com.faraya.legioss.core.dao.costing.IPieceworkDAO;
import com.faraya.legioss.core.dao.payroll.IEmployeeDAO;
import com.faraya.legioss.core.dao.payroll.chart.IChartNodeDAO;
import com.faraya.legioss.core.dao.payroll.chart.IOrgChartDAO;
import com.faraya.legioss.core.dao.security.IUserDAO;
import com.faraya.legioss.core.entity.common.*;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.agreement.HoursAgreement;
import com.faraya.legioss.core.entity.payroll.agreement.PieceworkAgreement;
import com.faraya.legioss.core.entity.payroll.chart.ChartNode;
import com.faraya.legioss.core.entity.payroll.chart.OrgChart;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.ns.NestedSetDaoIT;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 10/5/15.
 */


public class OrgChartIT extends TransactionalSpringJUnit4RunnerTest {

    Logger logger = LoggerFactory.getLogger(NestedSetDaoIT.class);

    @Autowired
    IBusinessDAO businessDAO;

    @Autowired
    IOrgChartDAO orgChartDAO;

    @Autowired
    IChartNodeDAO chartNodeDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IEmployeeDAO employeeDAO;

    @Autowired
    IPieceworkDAO pieceworkDAO;

    @Test
    public void simpleChartTest() {

        Business business = new Business("LegiossSoft", BasicCurrency.crc(), new Period(LocalDate.now().plusYears(1)));
        businessDAO.save(business);

        OrgChart chart = new OrgChart(business);
        orgChartDAO.save(chart);

        ChartNode root = new ChartNode(business);
        chartNodeDAO.add(root, chart);

        User userCEO = new User("ceo@legios.net", "fn", "ln");
        userDAO.save(userCEO);

        Piecework pw1 = new Piecework("0001", "big-boxes-1");
        pieceworkDAO.save(pw1);
        Employee ceo = new Employee(userCEO, LocalDate.now());
        Agreements agreements1 = new Agreements(DailyWorkSchedule.regularEightHoursWorkday(), BasicMoney.ofCRC(0));
        agreements1.addAgreement(new PieceworkAgreement(
                        new Period(),
                        BasicMoney.ofCRC(2),
                        pw1
                )
        );

        ceo.setAgreements(agreements1);

        employeeDAO.save(ceo);
        assertNotNull("null", ceo.getId());

        ChartNode ceoNode = new ChartNode(ceo, "CEO", business);
        chartNodeDAO.add(ceoNode, root, chart);

        User userCFO = new User("cfo@legios.net", "fn", "ln");
        userDAO.save(userCEO);

        Piecework pw2 = new Piecework("0002", "big-boxes-2");
        pieceworkDAO.save(pw2);

        Employee cfo = new Employee(userCFO, LocalDate.now());
        Agreements agreements2 = new Agreements(DailyWorkSchedule.regularEightHoursWorkday(), BasicMoney.ofCRC(0));
        agreements2.addAgreement(new PieceworkAgreement(
                        new Period(),
                        BasicMoney.ofCRC(2),
                        pw2
                )
        );

        agreements2.addAgreement(
                new HoursAgreement(
                        new Period(),
                        DailyWorkSchedule.regularEightHoursWorkday(),
                        BasicMoney.ofCRC(0))
        );

        cfo.setAgreements(agreements2);


        employeeDAO.save(cfo);
        assertNotNull("null", cfo.getId());

        ChartNode cfoNode = new ChartNode(cfo, "CFO", business);
        chartNodeDAO.add(cfoNode, ceoNode, chart);

        User userCOO = new User("coo@legios.net", "fn", "ln");
        userDAO.save(userCEO);
        Employee coo = new Employee(userCOO, LocalDate.now());

        pieceworkDAO.save(pw2);
        Agreements agreement3 = new Agreements(DailyWorkSchedule.regularEightHoursWorkday(), BasicMoney.ofCRC(2));
        agreement3.addAgreement(new PieceworkAgreement(
                        new Period(),
                        BasicMoney.ofCRC(2), pw2
                )
        );

        agreement3.addAgreement(
                new HoursAgreement(
                        new Period(),
                        DailyWorkSchedule.regularEightHoursWorkday(),
                        BasicMoney.ofCRC(2))
        );

        coo.setAgreements(agreement3);
        employeeDAO.save(coo);
        assertNotNull("null", coo.getId());

        ChartNode cooNode = new ChartNode(coo, "CFO", business);
        chartNodeDAO.add(cooNode, ceoNode, chart);

        User userCIO = new User("cio@legios.net", "fn", "ln");
        userDAO.save(userCIO);

        Employee cio = new Employee(userCIO, LocalDate.now());
        Agreements agreement4 = new Agreements(DailyWorkSchedule.regularEightHoursWorkday(), BasicMoney.ofCRC(0));
        agreement4.addAgreement(new PieceworkAgreement(
                        new Period(),
                        BasicMoney.ofCRC(2), pw2
                )
        );

        cio.setAgreements(agreement4);
        employeeDAO.save(cio);
        assertNotNull("null", cio.getId());
        ChartNode cioNode = new ChartNode(cio, "CFO", business);
        chartNodeDAO.add(cioNode, ceoNode, chart);

    }

}
