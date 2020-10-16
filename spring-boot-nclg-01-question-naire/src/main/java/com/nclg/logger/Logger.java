package com.nclg.logger;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 16:03
 **/
public class Logger {
    private static final boolean FLAG = true;

    public static void logger(Class c, String info) {
        if (FLAG) {
            System.out.println(c + " -- 打印日志信息：\t" + info);
        }
    }

}
