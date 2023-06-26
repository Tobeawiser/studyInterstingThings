package com.example.lovestory.util.test.xmlparse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MarshallContext {
    //
//    public static void main(String[] args) throws Exception {
//        String xml = "<RECORD>   " +
//                "<NAME>  lyle  </NAME>" +
//                "<AGE>  25  </AGE>" +
//                " </RECORD>";
//
//        MessageXml messageXml = xmlToObject(new MessageXml(), xml);
//        System.out.println(JSONObject.toJSONString(messageXml));
//
//        MessageXml messageXml1 = new MessageXml();
//        messageXml1.setName("lule");
//        messageXml1.setAge("28");
//        objectToXml(messageXml1);
//
//    }
    public static void testConvert() {
        //加入注解
        //获取方法返回对象
        //将xml转换为对象class
        //对象class是不确定的
        //给两个对象找一个共同
        //转换需要指明具体的class


        //1 转换异常  捕获具体异常  再转换成991   内部抛出转换异常  业务层aop全局捕获 用resp对xml进行返回具体code 业务层根据code处理
        // 拿到xml信息进行991转换
        // 优化： 可以用额外字段把转换封在里面  同步和异步是如此

        //2
    }

    /**
     * <T> 表示持有一个泛型
     *
     * @param object
     * @param <T>
     */
    public static <T> T xmlToObject(T object, String xml) {
        T result = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException jaxbException) {
            throw new RuntimeException(jaxbException.getMessage());
        } catch (Exception e) {
            System.out.println("进入Exception");
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        String xml = "<RECORD>   " +
                "<NAME>  lyle  </NAME>" +
                "<AGE>  25  </AGE>" +
                " </RECORD>";

        MessageXml messageXml = xmlToObject(new MessageXml(), xml);
        System.out.println(messageXml);
        Object obj = new Object();
        String perFirstDayOfMonth = getPerFirstDayOfMonth();
        System.out.println("perFirstDayOfMonth:" + perFirstDayOfMonth);


    }

    /**
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getPerFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }


    /**
     * <T> 表示持有一个泛型
     *
     * @param object
     */
    public static String objectToXml(Object object) {
        String result = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.marshal(object, System.out);
        } catch (JAXBException jaxbException) {
            System.out.println("转换异常" + jaxbException);
        }
        return result;
    }
}
