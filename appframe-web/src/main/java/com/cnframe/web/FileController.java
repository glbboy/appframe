package com.cnframe.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class FileController extends BaseController {
	@Value("${uploadPath}")
    private String uploadPath;
	// 文件上传相关代码
	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(@RequestParam("test") MultipartFile file) {
		if (file.isEmpty()) {
			return "文件为空";
		}
		transferTo(file);
		return "上传失败";
	}

	// 文件下载相关代码
	@RequestMapping("/download")
	public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "FileUploadTests.java";
		if (fileName != null) {
			// 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
			String realPath = request.getServletContext().getRealPath("//WEB-INF//");
			File file = new File(realPath, fileName);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("success");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();						}
					}
				}
			}
		}
		return null;
	}

	// 多文件上传
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST,produces = "application/json;charset=utf8")
	@ResponseBody
	 public Map<String, Object> batchUploadFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.info((String) request.getParameter("exampleInputEmail1"));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					transferTo(file);
				} catch (Exception e) {
					dataMap.put("result", "You failed to upload " + i + " => " + e.getMessage());
					e.printStackTrace();
				}
			} else {
				dataMap.put("result", "You failed to upload " + i + " because the file was empty");
			}
		}	
		dataMap.put("result", "success");
        return dataMap;
	}

	public void transferTo(MultipartFile file) {
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String filePath = uploadPath+Calendar.getInstance().get(Calendar.YEAR)+"//";
		// 解决中文问题，liunx下中文路径，图片显示问题
		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
