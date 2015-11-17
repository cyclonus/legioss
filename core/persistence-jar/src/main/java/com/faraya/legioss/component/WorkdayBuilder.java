package com.faraya.legioss.component;

import com.faraya.legioss.core.entity.common.Workday;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 *
 * Created by fabrizzio on 11/16/15.
 */

@Component
public class WorkdayBuilder {

    public Workday regularEightHoursWorkday(){
        return new Workday(LocalTime.of(8,0),LocalTime.of(17,0));
    }

    public Workday fromToHours(int from, int to){
        return new Workday(LocalTime.of(from,0),LocalTime.of(to,0));
    }
}
