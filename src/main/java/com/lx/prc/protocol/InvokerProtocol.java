package com.lx.prc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义协议
 * @author leon
 */
@Data
public class InvokerProtocol implements Serializable {
    /**
     * 类名
     */
    private String className;
    /**
     * 函数名称
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parames;
    /**
     * 参数类别
     */
    private Object[] values;
}
