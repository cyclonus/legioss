package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.dao.costing.IPieceworkDAO;
import com.faraya.legioss.core.dao.payroll.IEmployeeDAO;
import com.faraya.legioss.core.dao.payroll.log.IPieceworkLogDAO;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.agreement.PieceworkAgreement;
import com.faraya.legioss.core.entity.payroll.log.PieceworkLog;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.PayrollContextBuilder;
import com.faraya.legioss.core.model.payroll.piecework.DailyPieceworkSalary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static com.faraya.legioss.service.payroll.PayrollMockUtils.*;
import static org.junit.Assert.*;


/**
 *
 * Created by fabrizzio on 2/8/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PieceworkSalaryServiceTest {

    @Mock
    ICalendarDAO calendarDAO;

    @Mock
    IEmployeeDAO employeeDAO;

    @Mock
    IPieceworkLogDAO pieceworkLogDAO;

    @Mock
    IPieceworkDAO pieceworkDAO;

    @InjectMocks
    PieceworkSalaryServiceImpl pieceworkSalaryService;

    @InjectMocks
    PayrollContextBuilder contextBuilder;

    @Test
    public void testDailyPayrollOneShiftAgreement() {

        when(pieceworkDAO.save(any(Piecework.class))).thenAnswer(new Answer<Piecework>() {
            @Override
            public Piecework answer(InvocationOnMock invocationOnMock) throws Throwable {
                Random rnd = new Random();
                final Long id = rnd.nextLong();
                final String name = "p-" + rnd.nextInt();
                Piecework p = mock(Piecework.class);
                when(p.getId()).thenReturn(id);
                when(p.getCode()).thenReturn(name);
                when(p.getName()).thenReturn(name);
                when(p.getDescription()).thenReturn("");
                return p;
            }
        });

        Piecework p1 = pieceworkSalaryService.addPiecework("p1", "p1", "");
        Piecework p2 = pieceworkSalaryService.addPiecework("p2", "p2", "");
        Piecework p3 = pieceworkSalaryService.addPiecework("p3", "p3", "");

        //Need to pass the price per piecework here
        Set<PieceworkParam> params = new HashSet<>();
        params.add(new PieceworkParam() {
            @Override
            public Piecework getPiecework() {
                return p1;
            }

            @Override
            public double getRate() {
                return 10;
            }
        });

        params.add(new PieceworkParam() {
            @Override
            public Piecework getPiecework() {
                return p2;
            }

            @Override
            public double getRate() {
                return 20;
            }
        });

        params.add(new PieceworkParam() {
            @Override
            public Piecework getPiecework() {
                return p3;
            }

            @Override
            public double getRate() {
                return 30;
            }
        });

        Set<PieceworkAgreement> pieceWorkAgreements = mockPieceworkAgreements(params);

        User user = mockUser();
        Employee employee = mockEmployee(user);

        Period period = mockPeriod(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
        PayrollContext context = mockPayrollContext();

        pieceworkSalaryService.addPieceworkLogEntry(p1.getId(), LocalDate.now().minusDays(2), employee.getId(), 10, null);
        pieceworkSalaryService.addPieceworkLogEntry(p2.getId(), LocalDate.now().minusDays(1), employee.getId(), 10, null);
        pieceworkSalaryService.addPieceworkLogEntry(p3.getId(), LocalDate.now(), employee.getId(), 10, null);

        Agreements agreements = mock(Agreements.class);
        when(employee.getAgreementId()).thenReturn(1L);
        when(employee.getCeaseDate()).thenReturn(null);
        when(employee.getAgreements()).thenReturn(agreements);

        PieceworkLog pl1 = mockPieceworkLog(p1, employee, 10);
        PieceworkLog pl2 = mockPieceworkLog(p2, employee, 10);
        PieceworkLog pl3 = mockPieceworkLog(p3, employee, 10);

        List<PieceworkLog> log = Arrays.asList(pl1,pl2,pl3);
        when(pieceworkLogDAO.findPieceworkLogBetween(employee.getId(), period.getStart(), period.getEnd())).thenReturn(log);

        when(agreements.getPieceworkAgreements()).thenReturn(pieceWorkAgreements);
        when(employeeDAO.findByPK(1L)).thenReturn(employee);

        DailyPieceworkSalary result = pieceworkSalaryService.computeDailySalary(employee.getId(), period, context);
        assertNotNull(result);
    }

}
