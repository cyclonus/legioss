package com.faraya.legioss.core.dao.payroll.log;

import com.faraya.legioss.core.dao.IGenericDAO;
import com.faraya.legioss.core.entity.common.Period;
import com.faraya.legioss.core.entity.payroll.log.DailyAttendance;

import java.util.List;

/**
 *
 * Created by fabrizzio on 11/17/15.
 */

public interface IAttendanceLogDAO  extends IGenericDAO<DailyAttendance,Long> {

    List<DailyAttendance> findAttendance(Long employeeId, Period period);

}
