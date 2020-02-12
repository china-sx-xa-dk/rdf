/**
 * Copyright &copy; 2016-2020 <a href="http://www.sxgokit.com">SXGOK</a> All rights reserved.
 */
package com.sxgokit.rdf.plugins.ckfinder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.ConnectorServlet;
import com.sxgokit.rdf.util.fileUtil.FileUtils;

/**
 * CKFinderConnectorServlet
 * @author SXGOK
 * @version V1.0.1
 */
@WebServlet(initParams = {
		@WebInitParam(name = "XMLConfig", value = "classpath:ckfinder.xml"),
		@WebInitParam(name = "debug", value = "false"),
		@WebInitParam(name = "configuration", value = "com.sxgokit.rdf.plugins.ckfinder.CKFinderConfig")
})
public class CKFinderConnectorServlet extends ConnectorServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, false);
		super.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, true);
		super.doPost(request, response);
	}

	private void prepareGetResponse(final HttpServletRequest request,
									final HttpServletResponse response, final boolean post) throws ServletException {
		String command = request.getParameter("command");
		String type = request.getParameter("type");
		// 初始化时，如果startupPath文件夹不存在，则自动创建startupPath文件夹
		if ("Init".equals(command)){
			String startupPath = request.getParameter("startupPath");// 当前文件夹可指定为模块名
			if (startupPath!=null){
				String[] ss = startupPath.split(":");
				if (ss.length==2){
					String path = "/static/common/userfiles/"+ss[0]+ss[1];
					String realPath = FileUtils.path(Thread.currentThread().getContextClassLoader().getResource("").getPath()) + path;
					FileUtils.createDirectory(realPath);
				}
			}
		}
		// 快捷上传，自动创建当前文件夹，并上传到该路径
		else if ("QuickUpload".equals(command) && type!=null){
			String currentFolder = request.getParameter("currentFolder");// 当前文件夹可指定为模块名
			String path = "/static/common/userfiles/"+type+(currentFolder!=null?currentFolder:"");
			String realPath = FileUtils.path(Thread.currentThread().getContextClassLoader().getResource("").getPath()) + path;
			FileUtils.createDirectory(realPath);
		}
//      System.out.println("------------------------");
//      for (Object key : request.getParameterMap().keySet()){
//          System.out.println(key + ": " + request.getParameter(key.toString()));
//      }
	}


	
}
