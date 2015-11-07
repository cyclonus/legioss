package com.faraya.legioss.core.model.calendar;

/**
 *
 * Created by fabrizzio on 11/2/15.
 */

import com.faraya.legioss.util.jackson.MonthDayDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.MonthDay;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "monthDay",
        "name",
        "mandatory"
})
public class CalendarEntryModel {

    @JsonProperty("monthDay")
    private MonthDay monthDay;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mandatory")
    private Boolean mandatory;

    /**
     *
     * @return
     * The monthDay
     */
    @JsonDeserialize(using = MonthDayDeserializer.class)
    @JsonProperty("monthDay")
    public MonthDay getMonthDay() {
        return monthDay;
    }

    /**
     *
     * @param monthDay
     * The monthDay
     */
    @JsonProperty("monthDay")
    public void setMonthDay(MonthDay monthDay) {
        this.monthDay = monthDay;
    }

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
     * The mandatory
     */
    @JsonProperty("mandatory")
    public boolean isMandatory(){
        return (mandatory == null ? true : mandatory);
    }

    /**
     *
     * @param mandatory
     * The mandatory
     */
    @JsonProperty("mandatory")
    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

}
