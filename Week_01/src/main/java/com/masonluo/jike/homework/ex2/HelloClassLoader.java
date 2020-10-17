package com.masonluo.jike.homework.ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author Masonluo
 * @date 2020-10-17 11:16
 */
public class HelloClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] clazzByte = getBytes("ex2/Hello/Hello.xlass");
        if (clazzByte == null) {
            throw new ClassNotFoundException("File Not Found");
        }
        for (int i = 0; i < clazzByte.length; i++) {
            clazzByte[i] = (byte) (255 - clazzByte[i]);
        }
        return defineClass(name, clazzByte, 0, clazzByte.length);
    }

    private byte[] getBytes(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        if (url == null) {
            return null;
        }
        File file = new File(url.getPath());
        System.out.println(file.exists());
        try {
            FileInputStream fis = new FileInputStream(url.getFile());
            byte[] res = new byte[fis.available()];
            fis.read(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HelloClassLoader classLoader = new HelloClassLoader();
        Class<?> clazz = classLoader.loadClass("Hello");
        Object hello = clazz.newInstance();
        Method method = clazz.getMethod("hello");
        method.invoke(hello);
    }
}
