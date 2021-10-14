package svip_homework.design.stratage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iris
 * @date 2021/10/13
 * <p>
 * 策略模式
 * 定义算法抽象,同时封装了一系列算法,使用时可以进行互相替换,
 * 主要针同一问题办法有很多种,需要自由切换算法规则的情况
 */
public class Strategy {

    public static void main(String[] args) {
        StrategyFactory strategyFactory = new StrategyFactory();
        IExportStrategy pdf = strategyFactory.getStrategyByType("PDF");
        pdf.export();
        IExportStrategy excel = strategyFactory.getStrategyByType("EXCEL");
        excel.export();


    }

}

class StrategyFactory {
    private static final Map<String, IExportStrategy> map = new HashMap<>();

    static {
        map.put("PDF", new PDFExport());
        map.put("EXCEL", new EXCELExport());
    }

    public IExportStrategy getStrategyByType(String type) {
        return map.get(type);
    }
}

interface IExportStrategy {
    void export();
}

class EXCELExport implements IExportStrategy {

    @Override
    public void export() {
        System.out.println("导出为excel文件");
    }
}

class PDFExport implements IExportStrategy {

    @Override
    public void export() {
        System.out.println("导出为PDF文件");
    }
}