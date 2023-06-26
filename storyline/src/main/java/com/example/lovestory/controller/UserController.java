package com.example.lovestory.controller;

import com.example.lovestory.dao.UserDao;
import com.example.lovestory.entity.User;
import com.example.lovestory.entity.UserReq;
import com.example.lovestory.learn.globalexception.BizException;
import com.example.lovestory.service.UserService;
import com.example.lovestory.util.test.GenerateUser;
import com.example.lovestory.util.test.annotations.MyAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
@MyAnnotation(hello = "helloChange")
public class UserController {


    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    private boolean running;

    @RequestMapping("/find")
    public User hello() {
        List<User> list = userDao.selectList(null);
        list.forEach(User::getName);
        list.get(0).setName("cicdTest0725TestTrigger");
        return list.get(0);
    }

    @PostMapping("/helloWorld")
    public String helloWorld(@RequestBody @Validated UserReq userReq) {

        return "helloWorld";
    }

    @RequestMapping("/insert")
    public List<User> insert() {
        List<User> users = GenerateUser.randomCreateUserInfo(10);
        users.forEach(u -> userDao.insert(u));
        return users;
    }

    @RequestMapping("/bizExceptionTest")
    public List<User> bizExceptionTest() {
        List<User> users = GenerateUser.randomCreateUserInfo(10);
        if (1 == 1) {
            throw new BizException("msg");
        }
        return users;
    }

    @RequestMapping("/exceptionTest")
    public List<User> exceptionTest() throws Exception {
        List<User> users = GenerateUser.randomCreateUserInfo(10);
        users.forEach(u -> userDao.insert(u));
        if (1 == 1) {
            throw new Exception("msg");
        }
        return users;
    }

    @RequestMapping("/otherExceptionTest")
    public List<User> otherExceptionTest() {
        List<User> users = GenerateUser.randomCreateUserInfo(10);
        userDao.insertException(null);
        return users;
    }


    // 注：// 获取项目下文件或者文件流// File file = new File(this.getClass().getResource("/xls/adminImportUserTemplate.xls").toURI());// in = new BufferedInputStream(this.getClass().getResourceAsStream("/xls/adminImportUserTemplate.xls"));    /**
    /* 下载文件到浏览器
     * @param request
     * @param response
     * @param filename 要下载的文件名
     * @param file     需要下载的文件对象
     * @throws IOException
     */
    @RequestMapping("/excel")
    public void downFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //  文件存在才下载
        String fileName = "C:\\Users\\86131\\Desktop\\日常\\五个任务\\excelTable.xls";
        File file = new File(fileName);
        if (file.exists()) {
            OutputStream out = null;
            FileInputStream in = null;
            try {
                // 1.读取要下载的内容
                in = new FileInputStream(file);

                // 2. 告诉浏览器下载的方式以及一些设置
                // 解决文件名乱码问题，获取浏览器类型，转换对应文件名编码格式，IE要求文件名必须是utf-8, firefo要求是iso-8859-1编码
                String encodeFileName = "";
                String agent = request.getHeader("user-agent");
                if (agent.contains("FireFox")) {
                    encodeFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
                } else {
                    //filename = URLEncoder.encode(filename, "UTF-8");
                    encodeFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
                }
                // 设置下载文件的mineType，告诉浏览器下载文件类型
                String mineType = request.getServletContext().getMimeType(encodeFileName);
                response.setContentType(mineType);
                // 设置一个响应头，无论是否被浏览器解析，都下载
                response.setHeader("Content-disposition", "attachment; filename=" + encodeFileName);
                // 将要下载的文件内容通过输出流写到浏览器
                out = response.getOutputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
        }
    }


}
