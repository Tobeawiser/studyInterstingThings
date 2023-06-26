package com.example.lovestory.util.test.xmlparse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RECORD")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageXml {

    @XmlElement(name = "NAME")
    private String name;

    @XmlElement(name = "AGE")
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
