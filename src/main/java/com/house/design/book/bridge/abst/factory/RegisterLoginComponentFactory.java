package com.house.design.book.bridge.abst.factory;

import com.house.design.book.bridge.abst.AbstractRegisterLoginComponent;
import com.house.design.book.bridge.abst.RegisterLoginComponent;
import com.house.design.book.bridge.function.RegisterLoginFuncInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RegisterLoginComponentFactory
 * @Author haoZe
 * @Date 2023/12/5
 **/
public class RegisterLoginComponentFactory {

    public static Map<String, AbstractRegisterLoginComponent> componentMap
            = new ConcurrentHashMap<>();

    public static Map<String, RegisterLoginFuncInterface> funcMap
            = new ConcurrentHashMap<>();

    public static AbstractRegisterLoginComponent getComponent(String type) {
        AbstractRegisterLoginComponent component =
                componentMap.get(type);
        if (component == null) {
            synchronized (componentMap) {
                component = componentMap.get(type);
                if (component == null) {
                    component = new RegisterLoginComponent(funcMap.get(type));
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }
}
