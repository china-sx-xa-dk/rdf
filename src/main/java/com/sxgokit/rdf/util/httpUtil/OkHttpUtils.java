/*
 * FileName：HttpUtils.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author:  tangchaojun
 * Version: V100R01C02
 * Time:2018年9月28日 下午4:07:23
 */

package com.sxgokit.rdf.util.httpUtil;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils
{
	/**
	 * LOG
	 */
	protected static final Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);

	private final static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(
		60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60,
		TimeUnit.SECONDS).addInterceptor(new LoggingInterceptor()).build();


	/**
	 *  这是按照官方的示例Interceptor改的，打印日志的方式换成了Log.d().
	 */
	static class LoggingInterceptor implements Interceptor
	{
		@Override
		public Response intercept(Chain chain)
			throws IOException
		{
			//第一步，获得chain内的request
			Request request = chain.request();
			logger.error("request info is {}", request.toString());
			//第二步，用chain执行request
			Response response = chain.proceed(request);
			logger.error("request info is {}", response.toString());
			//第三步，返回response
			return response;
		}
	}

	/**
	 * doGet
	 * Description: <br>
	 * Time：2019年2月14日 上午11:30:21<br>
	 * @author wangxu
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String url)
		throws Exception
	{
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
		{
			return response.body().string();
		}
		else
		{
			throw new IOException("OkHttpUtils doGet response=" + response);
		}
	}

	/**
	 * doPost
	 * Description: <br>
	 * Time：2019年4月4日 上午10:23:14<br>
	 * @author wuhao
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static byte[] sendGetByte(String url)
		throws Exception
	{
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
		{
			return response.body().bytes();
		}
		else
		{
			throw new IOException("OkHttpUtils doGet response=" + response);
		}
	}

	/**
	 * doPost
	 * Description: <br>
	 * Time：2019年2月14日 上午11:30:31<br>
	 * @author wangxu
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String url, String json)
		throws Exception
	{

		MediaType mediaType = MediaType.parse(
			"application/json; charset=utf-8");
		RequestBody body = RequestBody.create(mediaType, json);

		Request request = new Request.Builder().url(url).post(body).addHeader(
			"cache-control", "no-cache").addHeader("content-type",
			"application/json").build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
		{
			return response.body().string();
		}
		else
		{
			throw new IOException("OkHttpUtils doPost response=" + response);
		}
	}

	/**
	 * doGet
	 * Description: <br>
	 * Time：2019年2月14日 上午11:30:21<br>
	 * @author wangxu
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static boolean download(String url, String saveDir, String fileName)
		throws Exception
	{
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful())
		{
			InputStream is = null;
			byte[] buf = new byte[2048];
			int len = 0;
			FileOutputStream fos = null;
			// 储存下载文件的目录
			File downloadFile = new File(saveDir);
			if (!downloadFile.mkdirs())
			{
				downloadFile.createNewFile();
			}
			String savePath = downloadFile.getAbsolutePath();
			//            String fileName = url.substring(url.lastIndexOf("/") + 1);
			try
			{
				is = response.body().byteStream();
				File file = new File(savePath, fileName);
				fos = new FileOutputStream(file);
				while ((len = is.read(buf)) != -1)
				{
					fos.write(buf, 0, len);
				}
				fos.flush();
				return true;
			}
			catch (Exception e)
			{
				throw new IOException("OkHttpUtils download response=" + e);
			}
			finally
			{
				try
				{
					if (is != null)
						is.close();
					if (fos != null)
						fos.close();
				}
				catch (IOException e)
				{
					throw new IOException("OkHttpUtils download response=" + e);
				}
			}
		}
		else
		{
			throw new IOException("OkHttpUtils download response=" + response);
		}
	}

	public static void main(String[] args)
		throws Exception
	{
		download(
			"http://www.liaozal.com:82/aoto/card/noun/file?dateId=20190402",
			"D:\\", "20190402.txt");
	}
}
