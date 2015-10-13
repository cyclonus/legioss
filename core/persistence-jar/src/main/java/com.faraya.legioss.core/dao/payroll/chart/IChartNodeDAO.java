package com.faraya.legioss.core.dao.payroll.chart;

import com.faraya.legioss.core.dao.ns.INestedSetDAO;
import com.faraya.legioss.core.entity.payroll.chart.ChartNode;
import com.faraya.legioss.core.entity.payroll.chart.OrgChart;

/**
 *
 * Created by fabrizzio on 10/4/15.
 */

public interface IChartNodeDAO extends INestedSetDAO<ChartNode,OrgChart>  {
}
