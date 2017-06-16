package com.wxccase.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * MyRequestWrapper.
 * 
 * @author havery
 */
public class JsonRequestWrapper extends HttpServletRequestWrapper {
	private final String body;

    public JsonRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuffer stringBuilder = new StringBuffer();
        byte[] b = new byte[1024];  
        int len = 0; 
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
            	//我真的要哭了  终于找到核心所在了  我操了草    这里给拿到body默认给搞成gbk了，我还需要转成utf8就麻烦的受不了的，在这里就要直接拿到utf8格式的
            	while ((len = inputStream.read(b)) != -1){  
            		stringBuilder.append(new String(b, 0, len, "utf-8")); 
                }  
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
        	if( inputStream != null){
        		 inputStream.close();
        	}
        }
        body = stringBuilder.toString();
    }
   
    @Override
    public ServletInputStream getInputStream() throws IOException {
      final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
      ServletInputStream servletInputStream = new ServletInputStream() {
        public int read() throws IOException {
          return byteArrayInputStream.read();
        }

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
			// TODO Auto-generated method stub
			
		}
      };
      return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
      return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }   
}