package com.faraya.legioss.core.entity.payroll.log;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.DailyWorkedHours;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * Created by fabrizzio on 11/6/15.
 */

@Entity
@Table(name = "payroll_daily_attendance",
        indexes =  {
                @Index(name = "date", columnList = "date"),
                @Index(name = "employee", columnList = "employee_id")
        }
)
public class DailyAttendance extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Embedded
    private DailyWorkedHours workedHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_type", nullable = false, length = 10)
    private AttendanceType attendanceType;

    @Column(name = "project_ref", nullable = true, length = 50)
    private String projectRef;

    public DailyAttendance(Long employeeId, LocalDate date, LocalTime timeIn, LocalTime timeOut, AttendanceType attendanceType, String projectRef) {
        this.employeeId = employeeId;
        this.date = date;
        this.workedHours = new DailyWorkedHours(timeIn, timeOut);
        this.attendanceType = attendanceType;
        this.projectRef = projectRef;
    }

    public DailyAttendance(Long employeeId, LocalDate date, LocalTime timeIn, LocalTime timeOut, AttendanceType attendanceType) {
        this(employeeId, date, timeIn, timeOut, attendanceType, null);
    }

    public DailyAttendance(Long employeeId, LocalDate date, LocalTime timeIn, LocalTime timeOut) {
        this(employeeId, date, timeIn, timeOut, AttendanceType.EFFECTIVE, null);
    }

    public DailyAttendance() {
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DailyWorkedHours getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(DailyWorkedHours workedHours) {
        this.workedHours = workedHours;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(String projectRef) {
        this.projectRef = projectRef;
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
