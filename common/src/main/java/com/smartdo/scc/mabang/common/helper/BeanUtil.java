package com.smartdo.scc.mabang.common.helper;

import org.apache.commons.lang3.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class BeanUtil {

    public static <T> T copyProperties(Object source, Class<T> editable) throws Exception {
        return copyProperties(source, editable, null);
    }

    /**
     * 实体属性复制
     *
     * @param source
     * @param editable
     * @param ignoreProperties
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> T copyProperties(Object source, Class<T> editable, String... ignoreProperties) throws Exception {
        T target = editable.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(editable);
        PropertyDescriptor[] targetPds = beanInfo.getPropertyDescriptors();
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            writeMethod.invoke(target, value);
                        } catch (Throwable var15) {
                            throw new Exception("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }
        return target;
    }

    public static PropertyDescriptor getPropertyDescriptor(Class<?> source, String propertyName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(source);
        PropertyDescriptor[] targetPds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pre : targetPds) {
            if (pre.getName().equals(propertyName))
                return pre;
        }
        return null;
    }

    /**
     * map属性复制到实体
     *
     * @param map
     * @param bean
     * @param clz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T setProperty(Map<String, Object> map, T bean, Class<T> clz) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                Object value = map.get(key);
                Method setter = property.getWriteMethod();
                setter.invoke(bean, value);
            }
        }
        return bean;
    }
}
