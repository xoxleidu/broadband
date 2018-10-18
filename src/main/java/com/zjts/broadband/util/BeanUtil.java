package com.zjts.broadband.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil {
	
	/**
     * cp dto和po集合转换
     * @param poList
     * @param voClass
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List copyList(List<? extends Object> poList, Class voClass) {

        List voList = new ArrayList();

        Object voObj = null;
        for (Object poObj : poList) {
            try {
                voObj = voClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(poObj, voObj);
            voList.add(voObj);
        }
        return voList;
    }

}
