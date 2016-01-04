package com.faraya.legioss.core.model.payroll;

import com.faraya.legioss.core.dao.calendar.ICalendarDAO;
import com.faraya.legioss.core.dao.common.IBusinessDAO;
import com.faraya.legioss.core.dao.common.IGlobalParamDAO;
import com.faraya.legioss.core.entity.calendar.Calendar;
import com.faraya.legioss.core.entity.calendar.CalendarDate;
import com.faraya.legioss.core.entity.common.Business;
import com.faraya.legioss.core.entity.common.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Created by fabrizzio on 12/27/15.
 */

@Component
public class PayrollContextBuilder {

    @Autowired
    ICalendarDAO calendarDAO;

    @Autowired
    IBusinessDAO businessDAO;

    @Autowired
    IGlobalParamDAO globalParamDAO;

    private Long businessId;

    private Period period;

    /**
     * TODO Cache this method
     * @param businessId
     * @param period
     * @return
     */
    private Map<LocalDate,CalendarDate> getCalendar(Long businessId, Period period){
        Calendar calendar = calendarDAO.findByBusinessId(businessId);
        Set<CalendarDate> calendarDates = calendar.getCalendarDates();
        return calendarDates.stream().filter(
                calendarDate -> period.isWithinPeriod(calendarDate.getDate())
        ).collect(Collectors.toMap(CalendarDate::getDate, calendarDate -> calendarDate));
    }

    public PayrollContextBuilder withBusinessId(Long businessId){
        this.businessId = businessId;
        return this;
    }

    public PayrollContextBuilder withPeriod(Period period){
        this.period = period;
        return this;
    }

    public PayrollContext build(){
        Business business = businessDAO.findByPK(this.businessId);
        Map<LocalDate,CalendarDate> calendarDateMap = getCalendar(this.businessId,this.period);
        return new PayrollContext(business, globalParamDAO.getHoursStandardShift(), calendarDateMap);
    }

}
