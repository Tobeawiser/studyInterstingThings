//package com.example.lovestory.util.test.uploadvideo;
//
//import com.aexit.entity.FileEntity;
//import com.aexit.service.FileService;
//import com.aexit.util.FileUploadTool;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class fileController {
//	@Autowired
//	private FileService service;
//
//	@RequestMapping(value = "/upload")
//	@ResponseBody
//	public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
//			HttpServletRequest request, ModelMap map) {
//		String message = "";
//		FileEntity entity = new FileEntity();
//		String logoPathDir = request.getParameter("shipin");
//		System.out.println("-------" + logoPathDir + "----------------------------------");
//		FileUploadTool fileUploadTool = new FileUploadTool();
//		try {
//			entity = fileUploadTool.createFile(logoPathDir, multipartFile, request);
//			if (entity != null) {
//				service.saveFile(entity);
//				message = "�ϴ��ɹ�";
//				map.put("entity", entity);
//				map.put("result", message);
//			} else {
//				message = "�ϴ�ʧ��";
//				map.put("result", message);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView("/pages/views/result", map);
//	}
//
//	@RequestMapping(value = "/{id}/play")
//	@ResponseBody
//	public ModelAndView playVideo(@PathVariable("id") long id, ModelMap model) {
//
//		// CharterDto charterDto_ = charterFacade.getCharterById(id);
//		FileEntity entity = service.findByid(id);
//		model.put("entity", entity.getPath());
//		ModelAndView view = new ModelAndView("index", model);
//		return view;
//	}
//
//	@RequestMapping("/show")
//	@ResponseBody
//	public ModelAndView showList(HttpServletRequest request, ModelMap map) {
//		// ��ȡ�ϴ��ļ�Ŀ¼
//		String logoPathDir = "/video/";
//		String uploadFilePath = request.getSession().getServletContext().getRealPath(logoPathDir);
//		// �洢Ҫ���ص��ļ���
//		List<String> fileNameMap = new ArrayList<String>();
//		this.listFile(new File(uploadFilePath), fileNameMap);
//		map.put("list", fileNameMap);
//		return new ModelAndView("listFile", map);
//
//	}
//
//	/**
//	 * @Description: �ݹ����ָ��Ŀ¼�µ������ļ�
//	 * @param file��������һ���ļ���Ҳ����һ���ļ�Ŀ¼
//	 * @param map���洢�ļ�����Map����
//	 */
//	public void listFile(File file, List<String> map) {
//		// ���file����Ĳ���һ���ļ�������һ��Ŀ¼
//		if (!file.isFile()) {
//			// �г���Ŀ¼�µ������ļ���Ŀ¼
//			File files[] = file.listFiles();
//			// ����files[]����
//			for (File f : files) {
//				// �ݹ�
//				listFile(f, map);
//			}
//		} else {
//			map.add(file.getName());
//		}
//	}
//
//	@RequestMapping(value = "/download") // ����
//	@ResponseBody
//	public void download(@RequestParam(value = "filename", required = false) String fileName,
//			HttpServletRequest request, ModelMap map, HttpServletResponse response) {
//		try {
//			fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		// ��ȡ�ϴ��ļ�Ŀ¼
//		String logoPathDir = "/video/";
//		String fileSaveRootPath = request.getSession().getServletContext().getRealPath(logoPathDir);
//		// �ļ�·��
//		String fileDir = fileSaveRootPath + File.separator + fileName;
//		File file = new File(fileDir);
//		if (!file.exists()) {
//			System.out.println("���ص��ļ�������");
//			return;
//		}
//		// ������Ӧͷ��������������ظ��ļ�
//		try {
//			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		// ��ȡҪ���ص��ļ������浽�ļ�������
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream(fileDir);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// ���������
//		OutputStream out;
//		try {
//			out = response.getOutputStream();
//			// ����������
//			byte buffer[] = new byte[1024];
//			int len = 0;
//			// ѭ�����������е����ݶ�ȡ������������
//			while ((len = in.read(buffer)) > 0) {
//				// ��������������ݵ��������ʵ���ļ�����
//				out.write(buffer, 0, len);
//			}
//			// �ر��ļ�������
//			in.close();
//			// �ر������
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}
