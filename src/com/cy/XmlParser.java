package com.cy;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class XmlParser extends DefaultHandler {

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        parseNode(qName, attributes);
    }

    private boolean findUnUsedResources;

    private void parseNode(String nodeName, Attributes attributes) {
//      parse include widget
        if (nodeName.equals("issue")) {
            for (int i = 0, len = attributes.getLength(); i < len; i++) {
                if (attributes.getQName(i).equals("id")
                        && "UnusedResources".equals(attributes.getValue(i))) {
                    findUnUsedResources = true;
                }
            }
        } else if (nodeName.equals("location") && findUnUsedResources) {
            for (int i = 0, len = attributes.getLength(); i < len; i++) {
                if (attributes.getQName(i).equals("file")) {
                    findUnUsedResources = false;
                    String path = attributes.getValue(i);
                    File file = new File(path);
                    if (file.exists()
                            && !file.getName().equals("strings.xml")
                            && !file.getName().equals("styles.xml")
                            && !file.getName().equals("colors.xml")
                            && !file.getName().equals("colors.xml")
                            && !file.getName().equals("dimens.xml")
                    ) {
                        file.delete();
                        System.out.println("删除：" + path);
                    }
                }
            }
        }
    }

    public static void start(String pathNameXml) {
        SAXParserFactory saxfa = SAXParserFactory.newInstance();
        XmlParser xmlParser = new XmlParser();
        try {
            SAXParser saxparser = saxfa.newSAXParser();
            InputStream is = new FileInputStream(pathNameXml);
            saxparser.parse(is, xmlParser);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
