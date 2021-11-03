package com.iris.spi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iris
 * @date 2021/11/3
 */
public class SpiServiceImpl implements SPIService{
    @Override
    public List<Object> listAllCompanyInfo() {
        System.out.println("开始执行第三方包，查找所有的公司返回");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("物业公司1");
        objects.add("物业公司2");
        return objects;
    }
}
