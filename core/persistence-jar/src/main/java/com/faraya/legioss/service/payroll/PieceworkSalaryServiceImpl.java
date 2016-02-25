package com.faraya.legioss.service.payroll;

import com.faraya.legioss.core.dao.costing.IPieceworkDAO;
import com.faraya.legioss.core.dao.payroll.IEmployeeDAO;
import com.faraya.legioss.core.dao.payroll.log.IPieceworkLogDAO;
import com.faraya.legioss.core.entity.common.BasicMoney;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.costing.Piecework;
import com.faraya.legioss.core.entity.payroll.Employee;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.payroll.agreement.HoursAgreement;
import com.faraya.legioss.core.entity.payroll.agreement.PayType;
import com.faraya.legioss.core.entity.payroll.agreement.PieceworkAgreement;
import com.faraya.legioss.core.entity.payroll.log.PieceworkLog;
import com.faraya.legioss.core.model.payroll.PayrollContext;
import com.faraya.legioss.core.model.payroll.piecework.DailyPieceworkSalary;
import com.faraya.legioss.core.model.payroll.piecework.PieceworkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by fabrizzio on 1/6/16.
 */
@Service
public class PieceworkSalaryServiceImpl implements IPieceworkSalaryService{

    @Autowired
    IEmployeeDAO employeeDAO;

    @Autowired
    IPieceworkDAO pieceworkDAO;

    @Autowired
    IPieceworkLogDAO pieceworkLogDAO;

    public Piecework addPiecework(String code, String name, String desc){
        Piecework piecework = new Piecework(code, name, desc);
        piecework = pieceworkDAO.save(piecework);
        return piecework;
    }

    public Piecework findPieceworkByCode(String code){
       return pieceworkDAO.findByCode(code);
    }

    public Piecework findPieceworkByName(String name){
        return pieceworkDAO.findByName(name);
    }

    public PieceworkLog addPieceworkLogEntry(Long pieceworkId, LocalDate date, Long employeeId, Integer unitCount, Long signedById){
        PieceworkLog pieceworkLog = new PieceworkLog(pieceworkId, date, employeeId, unitCount, signedById);
        pieceworkLogDAO.save(pieceworkLog);
        return pieceworkLog;
    }

    public DailyPieceworkSalary computeDailySalary(Long employeeId, Period period, PayrollContext context){
        final Employee employee = employeeDAO.findByPK(employeeId);
        LocalDate startDate = period.getStart();
        LocalDate endDate = period.getEnd();
        //TODO: filter agreements by validity and period
        Map<Piecework,PieceworkAgreement> agreements = getActivePieceworkAgreementByType(employee);
        // throw exception if no agreements are set
        if(agreements == null || agreements.isEmpty()){
           throw new RuntimeException("");
        }
        List<PieceworkLog> pieceworkLogEntries = pieceworkLogDAO.findPieceworkLogBetween(employeeId, startDate, endDate);
        Set<PieceworkDetail> details = getPieceWorkDetails(pieceworkLogEntries, agreements);
        Map<Currency,BigDecimal> totals = computeDailyTotals(details);
        return new DailyPieceworkSalary(totals, details);
    }

    private Set<PieceworkDetail> getPieceWorkDetails(List<PieceworkLog> pieceworkLogEntries, Map<Piecework,PieceworkAgreement> agreements){
        Set<PieceworkDetail> details = new HashSet<>();
        for(PieceworkLog entry: pieceworkLogEntries){
            Piecework piecework = entry.getPiecework();
            String code = piecework.getCode();
            PieceworkAgreement agreement = agreements.get(piecework);
            BasicMoney ba = agreement.getRate();
            Currency currency = ba.getCurrency().toCurrency();
            BigDecimal rate  = ba.getAmount();
            BigDecimal count = BigDecimal.valueOf(entry.getUnitCount());
            BigDecimal amount = rate.multiply(count);
            PieceworkDetail detail = new PieceworkDetail(code, count, currency, rate, amount);
            details.add(detail);
        }
        return details;
    }

    private Map<Currency,BigDecimal> computeDailyTotals(final Set<PieceworkDetail>  pieceworkDetails){
        Map<Currency,BigDecimal> result = new HashMap<>();
        pieceworkDetails.forEach(pieceWorkDetail -> {
            BigDecimal rate = pieceWorkDetail.getRate();
            Currency currency = pieceWorkDetail.getCurrency();
            result.compute(currency, (k, v) -> v == null ? rate.multiply(pieceWorkDetail.getAmount()) : v.add(rate.multiply(pieceWorkDetail.getAmount())));
        });
        return result;
    }

    private Map<Piecework,PieceworkAgreement> getActivePieceworkAgreementByType(Employee employee){
       Agreements agreements = employee.getAgreements();
       Set<PieceworkAgreement> pieceworkAgreements = agreements.getPieceworkAgreements();
       return pieceworkAgreements.stream().filter(PieceworkAgreement::isActive).collect(Collectors.toMap(PieceworkAgreement::getPiecework, pieceworkAgreement -> pieceworkAgreement));
    }

}
