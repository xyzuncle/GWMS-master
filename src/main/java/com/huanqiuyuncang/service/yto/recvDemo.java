package com.huanqiuyuncang.service.yto;

import com.huanqiuyuncang.util.DateUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xyz on 2017/5/5.
 */
@WebServlet(   name = "recvDemo",
                urlPatterns ={"/recvDemo/api"},
                loadOnStartup=1
            )
public class recvDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(recvDemo.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recvDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at33333: ").append(request.getContextPath());
        logger.debug("我被调用。。。。我是loger...");
        logger.info("我被调用。。。。我是loger...");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String timestamp = DateUtil.getTime();//时间戳
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String json = new String(request.getParameter("RequestData"));
        // TODO: json字符串内容就是你要解析的请求内容
        String responseResult = "{\"EBusinessID\":\"1287241\",\"UpdateTime\":\""+timestamp+"\",\"Success\": %s,\"Reason\":\"%s\"}";
        String writeResult = "";
        if (!json.isEmpty()) {
            writeResult = String.format(responseResult, "true", "");
            logger.info("这是推送的内容。。。。。"+json);
        } else {
            writeResult = String.format(responseResult, "false", "RequestData内容为空");
        }
        response.getWriter().write(writeResult);

    }
}

