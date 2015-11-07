package com.faraya.legioss.core.model.calendar;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "name",
        "calendar"
})
public class CalendarModel {

    @JsonProperty("name")
    private String name;
    @JsonProperty("calendar")
    private List<CalendarEntryModel> calendar = new ArrayList<CalendarEntryModel>();

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The calendar
     */
    @JsonProperty("calendar")
    public List<CalendarEntryModel> getCalendar() {
        return calendar;
    }

    /**
     *
     * @param calendar
     * The calendar
     */
    @JsonProperty("calendar")
    public void setCalendar(List<CalendarEntryModel> calendar) {
        this.calendar = calendar;
    }

}


