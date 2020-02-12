/**
 * Copyright &copy; 2016-2020 <a href="http://www.sxgokit.com">SXGOK</a> All rights reserved.
 */
package com.sxgokit.rdf.plugins.ckfinder;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.ckfinder.connector.ServletContextFactory;
import com.ckfinder.connector.configuration.Configuration;
import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.utils.AccessControlUtil;
import com.sxgokit.rdf.util.fileUtil.FileUtils;

/**
 * CKFinder配置
 * @author SXGOK
 * @version V1.0.1
 */
public class CKFinderConfig extends Configuration {

	public CKFinderConfig(ServletConfig servletConfig) {
        super(servletConfig);  
    }

	@Override
	protected Configuration createConfigurationInstance() {
		boolean isView = true;
		boolean isUpload = true;
		boolean isEdit = true;
		AccessControlLevel alc = this.getAccessConrolLevels().get(0);
		alc.setFolderView(isView);
		alc.setFolderCreate(isEdit);
		alc.setFolderRename(isEdit);
		alc.setFolderDelete(isEdit);
		alc.setFileView(isView);
		alc.setFileUpload(isUpload);
		alc.setFileRename(isEdit);
		alc.setFileDelete(isEdit);
		AccessControlUtil.getInstance(this).loadACLConfig();
		try {
			this.baseURL = FileUtils.path(ServletContextFactory.getServletContext().getContextPath()+"/static/common/userfiles/"
					+"/");
			this.baseDir = FileUtils.path(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/static/common/userfiles/"+"/");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new CKFinderConfig(this.servletConf);
	}

	@Override
	public boolean checkAuthentication(final HttpServletRequest request) {
		//权限验证
		return true;
	}

	public static String getUserfilesBaseDir() {
		String dir = "";
		try {
			dir = ServletContextFactory.getServletContext().getRealPath("/");
		} catch (Exception e) {
			return "";
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}

		return dir;
	}

}
