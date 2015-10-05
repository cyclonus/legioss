package com.faraya.legioss.core.dao.payroll.chart;

import com.faraya.legioss.core.dao.ns.NestedSetTreeDAO;
import com.faraya.legioss.core.entity.payroll.chart.OrgChart;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by fabrizzio on 10/4/15.
 */
@Repository
public class OrgChartDAO extends NestedSetTreeDAO<OrgChart> implements IOrgChartDAO {

    public OrgChartDAO() {
        super(OrgChart.class);
    }
}
