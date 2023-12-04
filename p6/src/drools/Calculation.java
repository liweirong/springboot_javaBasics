package drools;

import lombok.Data;

/**
 * @author iris
 * @date 2023/12/4
 */
@Data
public class Calculation {

    /**
     * 月份
     */
    private Integer month;
    /**
     * 截至目前累加
     */
    private double total;

    private double wage;//税前工资
    private double wageMore;//应纳税所得额
    private double cess;//税率
    private double specialDeduction;//专项扣除
    private double preMinus;//速算扣除数
    private double wageMinus;//扣税额
    private double actualWage;//税后工资
}
