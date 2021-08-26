package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);

        String xmlTag = "<" + tagName;
        String xmlContent = sw.toString();
        String xmlComment = "<!--" + comment + "-->\n";

        int index = 0;
        while ((index = xmlContent.indexOf(xmlTag, index)) != -1) {
            String beginXmlContent = xmlContent.substring(0, index);
            String endXmlContent = xmlContent.substring(index);
            xmlContent = beginXmlContent + xmlComment + endXmlContent;

            index += xmlComment.length() + xmlTag.length();
        }

        return xmlContent;
    }

    public static void main(String[] args) throws JAXBException {

    }
}
