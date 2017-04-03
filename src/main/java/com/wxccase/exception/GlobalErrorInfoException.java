/**
 * viakiba
 * 2017-04-02
 * 892645423@qq.com
 */
package com.wxccase.exception;

import com.wxccase.exception.interfaces.MesscodeInterface;

/**
 * @author viakiba
 */
public class GlobalErrorInfoException extends Exception{
	private MesscodeInterface errorInfo;

    public GlobalErrorInfoException (MesscodeInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public MesscodeInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(MesscodeInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
