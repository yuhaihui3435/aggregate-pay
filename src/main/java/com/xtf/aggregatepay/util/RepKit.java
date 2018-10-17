package com.xtf.aggregatepay.util;

import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RepKit {

    public static void writeHtml(HttpServletResponse response, Object o){
        response.reset();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= null;
        try {
            out = response.getWriter();
            out.append(o.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null) {
                out.flush();
                out.close();
            }
        }

    }

    public static void writeJson(HttpServletResponse response, Object o){
        response.reset();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out= null;
        try {
            out = response.getWriter();
            out.append(o.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null) {
                out.flush();
                out.close();
            }
        }


    }
}
