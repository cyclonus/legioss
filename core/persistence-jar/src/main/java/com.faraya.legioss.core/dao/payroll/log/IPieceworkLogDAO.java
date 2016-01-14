package com.faraya.legioss.core.dao.payroll.log;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.payroll.log.PieceworkLog;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */
public interface IPieceworkLogDAO extends IGenericDAO<PieceworkLog,Long>{

    List<PieceworkLog> findPieceworkLogBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

}
