package com.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class BossCrawler {

    //加密因子，可根据您的需要自定义
    private static final String ALGORITHM_NAME = "AES";
    private static final String DEFAULT_ENCRYPT_RULE = "AES/CBC/PKCS5Padding";
    private static final String RANDOM_KEY_ALGORITHM = "SHA1PRNG";
    private static final String RANDOM_KEY_ALGORITHM_PROVIDER = "SUN";


    public static String decrypt(String encrypted) {
        if (StringUtils.isEmpty(encrypted)) {
            return null;
        }
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_NAME);
            SecureRandom secureRandom = SecureRandom.getInstance(RANDOM_KEY_ALGORITHM, RANDOM_KEY_ALGORITHM_PROVIDER);
            secureRandom.setSeed(DEFAULT_ENCRYPT_RULE.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey originalKey = keyGenerator.generateKey();
            SecretKey secretKey = new SecretKeySpec(originalKey.getEncoded(), ALGORITHM_NAME);
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decrypted, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {


        //连接
        //1. 确定首页的url:
        String indexUrl = "https://www.douban.com/search?q=java";
//        String indexUrl = "";
        //2. 发送请求, 获取数据
        //2.1 获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.2 创建请求方式对象
        //HttpPost httpPost = new HttpPost(indexUrl);
        HttpGet httpGet = new HttpGet(indexUrl);

        //2.3 封装请求参数, 和请求头
//        LoginInfo loginInfo = new LoginInfo();
//        loginInfo.setUserId("odis");
//        loginInfo.setPassword("047427ca382ba3f663dc277fe5c0438751420deaae9b9b7ddbbaa200390416939878e389ad40f19dcc2b7e283225b1ff163690050331a425b0e88e6cf8a92663913bc028d3bd38b799e2b3e739bf5c471e1f16aa178aef57168f28edda0345746e7f6f8e36c681");
//        HttpEntity entity = new StringEntity(JSONObject.toJSONString(loginInfo));
//        httpPost.setEntity(entity);
        httpGet.setHeader("Content-Type", "application/json;charset=utf-8");
//        httpPost.setHeader("Cookie","wd_guid=8539c266-53fd-492d-a259-d092376eb14c; historyState=state; _bl_uid=g3lga5eb4CI0bLma5u9ajm8gpghp; acw_tc=0b328f3616580230577636262e01237ba3b90a2f0437c18eababe8f0602f93; lastCity=100010000; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1656513294,1656774240,1658019956,1658023059; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1658023070; __c=1658023058; __l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3D%26city%3D101270100%26position%3D100101&r=&g=&s=3&friend_source=0&s=3&friend_source=0; __a=99683068.1656774241.1658019957.1658023058.20.3.3.20; __zp_stoken__=cb6cdC2lGbU1yaQIeYW9dLU1DH1t6UzgrG21XBV1SO0Epe0hVbBg%2FQXUHeWxhRhE7Gn9uEi19WWt0eHE8JG0iGE8LDBRATBh1SlkncxlHEy9fGH1sKSg9PWgdW2RWFHgnWEZcbC13VEA2PA0%3D");

        // 设计一个头:  referer  防掉链//
        //httpPost.setHeader("Referer", "https://www.zhipin.com/web/geek/job?query=&city=101270100&position=100101");
        //2.4 发送请求, 获取响应的对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.getStatusLine());
        HttpEntity entity1 = response.getEntity();
        String result = EntityUtils.toString(entity1, "utf-8");
        System.out.println(result);

//        Connection connect = Jsoup.connect("https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=&city=101270100&experience=&degree=&industry=&scale=&stage=&position=100101&salary=&multiBusinessDistrict=&page=1&pageSize=30");
//        //connect.cookie("cookie","historyState=state; wd_guid=8539c266-53fd-492d-a259-d092376eb14c; _bl_uid=g3lga5eb4CI0bLma5u9ajm8gpghp; acw_tc=0b328f3616580230577636262e01237ba3b90a2f0437c18eababe8f0602f93; lastCity=100010000; __c=1658023058; __g=-; __l=l=/www.zhipin.com/c101190100-p100101/?ka=search_100101&r=&g=&s=3&friend_source=0; __a=99683068.1656774241.1658019957.1658023058.18.3.1.18; __zp_stoken__=cb6cdC2lGbU1yKAAJUng6LU1DH1tpAl1SbjRXBV1SO1c2bQUUbBg/QXUHeUw0L0RFGn9uEi19WWsKeBY8KgBABmEDHgZrSHROEF4lcxlHEy9fGAM5QH0dPWgdW2RWFHgnWEZcbC13VEA2PA0=; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1656513294,1656774240,1658019956,1658023059; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1658023059; __zp_sseed__=HfA4W1lcvpyBsjrad6+SrAsH+adfAlbbO6ZSuXzvKTA=; __zp_sname__=860db9de; __zp_sts__=1658023069431");
//        Document document = connect.get();
//        String html = document.html();
//        System.out.println(html);
    }

    public void AESDataHandle() {
        String aesDate = "mW4v1/1obCrPSUwVJ47gK3ku+E4NQ52K/l4QbkmBlbsJu1kR6Ui22rcI4Q1l9XTOCL51lt9APAfy457S5MnwMLlszLqt6dNbX5Abd+4RUwy8gAAEk+yXcGyNF8KJ4jag7YOrqxMFUmqPgOwoqT8qxw1eolOfFDbdG5r0LWKFJEHuoh4DAkVl8DSeNaZxgPTl2tHdRdDSdhy23MQH9ZMG2JC9THlS+GKatOBsYeFfXGpYRiW+nUeyQKzsgiarXraPzoFcJmdeqqUKSopN69yT71LR5AGyXrX/hggmqOwphOCkgA6AjWjddppv9cvcr5yvTO6c3U2wGeGUlAFfushSltGG4bf1qyDY2FeyXGIWMJqmTrgF6DV8B5EHczqEN5o8";

        String key = "c558Gq0YQK2QUlMc";

        String s = decrypt(aesDate);
        System.out.println("解密后：" + s);
    }
}
