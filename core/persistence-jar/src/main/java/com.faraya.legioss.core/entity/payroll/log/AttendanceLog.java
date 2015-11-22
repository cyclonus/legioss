package com.faraya.legioss.core.entity.payroll.log;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.DailyWorkedHours;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * Created by fabrizzio on 11/6/15.
 */

@Entity
@Table(name = "attendance_log")
public class AttendanceLog extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DailyWorkedHours dailyWorkedHours;

    //absentDay, sickDay, ppt, holiday

    @JoinColumn(name = "employee_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    @Column(name = "employee_id", nullable = false, updatable = false, insertable = false)
    private Long employeeId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public DailyWorkedHours getDailyWorkedHours() {
        return dailyWorkedHours;
    }

    public void setDailyWorkedHours(DailyWorkedHours dailyWorkedHours) {
        this.dailyWorkedHours = dailyWorkedHours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }


}
