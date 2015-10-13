package com.faraya.legioss.core.payroll;

import com.faraya.legioss.TransactionalSpringJUnit4RunnerTest;
import com.faraya.legioss.core.dao.common.IBusinessDAO;
import com.faraya.legioss.core.dao.common.ICurrencyDAO;
import com.faraya.legioss.core.dao.costing.IPieceworkDAO;
import com.faraya.legioss.core.dao.payroll.IEmployeeDAO;
import com.faraya.legioss.core.dao.payroll.chart.IChartNodeDAO;
import com.faraya.legioss.core.dao.payroll.chart.IOrgChartDAO;
import com.faraya.legioss.core.dao.security.IUserDAO;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.common.Currency;
import com.faraya.legioss.core.entity.common.Money;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreement;
import com.faraya.legioss.core.entity.payroll.agreement.PiceworkAgreement;
import com.faraya.legioss.core.entity.payroll.chart.ChartNode;
import com.faraya.legioss.core.entity.payroll.chart.OrgChart;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.ns.NestedSetDaoIT;
import com.faraya.legioss.util.DateUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by fabrizzio on 10/5/15.
 */


public class OrgChartIT extends TransactionalSpringJUnit4RunnerTest {

    Logger logger = LoggerFactory.getLogger(NestedSetDaoIT.class);

    @Autowired
    ICurrencyDAO currencyDAO;

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
    public void simpleChartTest(){

        Piecework pieceworkBoxes1 = new Piecework("0001","small-boxes");
        pieceworkDAO.save(pieceworkBoxes1);

        Piecework pieceworkBoxes2 = new Piecework("0002","big-boxes");
        pieceworkDAO.save(pieceworkBoxes2);

        Currency crc =  new Currency("CR Colon","CRC","£");//Factory method??
        currencyDAO.save(crc);

        Business business = new Business("LegiossSoft",crc ,new Period(DateUtils.computeYearsFrom(new Date(),1)));
        businessDAO.save(business);

        OrgChart chart = new OrgChart(business);
        orgChartDAO.save(chart);

        ChartNode root = new ChartNode(business);
        chartNodeDAO.add(root, chart);

        User userCEO = new User("ceo@legios.net","fn","ln");
        userDAO.save(userCEO);

        Employee ceo = new Employee(userCEO,new Date());
        Agreement agreement1 = new Agreement();
        agreement1.addPieceworkAgreement(new PiceworkAgreement(
                        new Period(),
                        new Money(new BigDecimal(1), crc), pieceworkBoxes1
                )
        );

        ceo.setAgreement(agreement1);

        employeeDAO.save(ceo);
        assertNotNull("null", ceo.getId());

        ChartNode ceoNode = new ChartNode(ceo,"CEO", business);
        chartNodeDAO.add(ceoNode, root, chart);

        User userCFO = new User("cfo@legios.net","fn","ln");
        userDAO.save(userCEO);

        Employee cfo = new Employee(userCFO,new Date());
        pieceworkDAO.save(pieceworkBoxes2);

        Agreement agreement2 = new Agreement();
        agreement2.addPieceworkAgreement(new PiceworkAgreement(
                        new Period(),
                        new Money(new BigDecimal(1), crc),pieceworkBoxes2
                        )
        );

        cfo.setAgreement(agreement2);


        employeeDAO.save(cfo);
        assertNotNull("null", cfo.getId());

        ChartNode cfoNode = new ChartNode(cfo,"CFO", business);
        chartNodeDAO.add(cfoNode, ceoNode, chart);

        User userCOO = new User("coo@legios.net","fn","ln");
        userDAO.save(userCEO);
        Employee coo = new Employee(userCOO,new Date());

        pieceworkDAO.save(pieceworkBoxes2);

        Agreement agreement3 = new Agreement();
        agreement3.addPieceworkAgreement(new PiceworkAgreement(
                        new Period(),
                        new Money(new BigDecimal(1), crc),pieceworkBoxes2
                )
        );

        coo.setAgreement(agreement3);
        employeeDAO.save(coo);
        assertNotNull("null", coo.getId());

        ChartNode cooNode = new ChartNode(coo,"CFO", business);
        chartNodeDAO.add(cooNode, ceoNode, chart);

        User userCIO = new User("cio@legios.net","fn","ln");
        userDAO.save(userCIO);

        Employee cio = new Employee(userCIO,new Date());
        pieceworkDAO.save(pieceworkBoxes2);
        Agreement agreement4 = new Agreement();
        agreement2.addPieceworkAgreement(new PiceworkAgreement(
                        new Period(),
                        new Money(new BigDecimal(1), crc),pieceworkBoxes2
                )
        );

        cio.setAgreement(agreement4);
        employeeDAO.save(cio);
        assertNotNull("null", cio.getId());
        ChartNode cioNode = new ChartNode(cio,"CFO", business);
        chartNodeDAO.add(cioNode, ceoNode, chart);

    }

}
