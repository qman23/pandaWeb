/*
IBM Confidential
 * (C) Copyright IBM Corp. 2011.
 * All Rights Reserved - Licensed Materials - Property of IBM
 */
package com.ibm.healthchecktool.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ibm.healthchecktool.bean.ConnBean;
import com.ibm.healthchecktool.exception.ExceptionStacktraceHandler;
import com.ibm.healthchecktool.log.Logger;


@SuppressWarnings("unchecked")
public class ConnResourceLoader {
    private static String XML_PATH;
    private static List<Element> beanList;
    private static List<Element> nodes;
    private static Document document;
    private static Element root;
    
    public static void configuration(String path) throws Exception{
        XML_PATH = path;
        SAXReader saxReader = new SAXReader();
        document = saxReader.read(new File(path));
        root = document.getRootElement();
    }
    
    public static int getSingleServerCount(String type){
        beanList = root.selectNodes("//ConnList/ConnBean");
        int i = 0;
        for(Element e:beanList){
            if(e.attributeValue("type").equals(type)){
                i++;
            }
        }
        return i;
    }
    
    public static ArrayList<ConnBean> getConnInfos(String type) {
        ArrayList<ConnBean> connList = new ArrayList<ConnBean>();
        beanList = root.selectNodes("//ConnList/ConnBean");
        for (int i = 0; i < beanList.size(); i++) {
            if(beanList.get(i).attributeValue("type").equals(type)){
                nodes = (beanList.get(i)).elements();
                ConnBean connBean = new ConnBean();
                connBean.setUUID(beanList.get(i).attributeValue("UUID"));
                connBean.setType(beanList.get(i).attributeValue("type"));
                connBean.setAlias(nodes.get(0).getText());
                connBean.setHost(nodes.get(1).getText());
                connBean.setPort(nodes.get(2).getText());
                connBean.setName(nodes.get(3).getText());
                connBean.setPwd(nodes.get(4).getText());
                connBean.setPath(nodes.get(5).getText());
                connList.add(connBean);
            }
        }
        
        return connList;
    }

    public static ConnBean getConnBeanById(String uid) {
        beanList = root.selectNodes("//ConnList/ConnBean");
        ConnBean connBean = new ConnBean();
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).attributeValue("UUID").equals(uid)) {
                nodes = (beanList.get(i)).elements();
                connBean.setUUID(uid);
                connBean.setType(beanList.get(i).attributeValue("type"));
                connBean.setAlias(nodes.get(0).getText());
                connBean.setHost(nodes.get(1).getText());
                connBean.setPort(nodes.get(2).getText());
                connBean.setName(nodes.get(3).getText());
                connBean.setPwd(nodes.get(4).getText());
                connBean.setPath(nodes.get(5).getText());
                break;
            }
        }
        return connBean;
    }

    @SuppressWarnings("deprecation")
    public static void addConnInfo(ConnBean bean) {
        try {

            Element connElement = (Element) root.selectSingleNode(
                    "//ConnList/ConnTemplate").clone();

            connElement.setName("ConnBean");

            connElement.setAttributeValue("UUID", IDGererator.getUUID());
            connElement.setAttributeValue("type", bean.getType());

            List<Element> tempNodes = connElement.elements();

            tempNodes.get(0).setText(bean.getAlias());
            tempNodes.get(1).setText(bean.getHost());
            tempNodes.get(2).setText(bean.getPort());
            tempNodes.get(3).setText(bean.getName());
            tempNodes.get(4).setText(bean.getPwd());
            tempNodes.get(5).setText(bean.getPath());

            root.add(connElement);

            XMLWriter writer = new XMLWriter(new FileOutputStream(XML_PATH));

            writer.write(document);

            writer.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error found when adding connection info.");
            String exceptionMessage = ExceptionStacktraceHandler.stackTraceToString(e);
            Logger.getLogger().error("Exception found \n" + exceptionMessage);
        }
    }

    public static void removeConnInfo(String uid) {
        try {
            beanList = root.selectNodes("//ConnList/ConnBean");
            for (int i = 0; i < beanList.size(); i++) {
                if (beanList.get(i).attributeValue("UUID").equals(uid)) {
                    
                    root.remove(beanList.get(i));

                    XMLWriter writer = new XMLWriter(new FileOutputStream(XML_PATH));

                    writer.write(document);

                    writer.close();

                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error found when updating connection info.");
            String exceptionMessage = ExceptionStacktraceHandler.stackTraceToString(e);
            Logger.getLogger().error("Exception found \n" + exceptionMessage);
        }
    }
    
    public static void updateConnInfo(ConnBean connBean) {
        try {
            beanList = root.selectNodes("//ConnList/ConnBean");
            for (int i = 0; i < beanList.size(); i++) {
                if (beanList.get(i).attributeValue("UUID").equals(connBean.getUUID())) {
                    
                    List<Element> tempNodes = beanList.get(i).elements();
                    
                    tempNodes.get(0).setText(connBean.getAlias());
                    tempNodes.get(1).setText(connBean.getHost());
                    tempNodes.get(2).setText(connBean.getPort());
                    tempNodes.get(3).setText(connBean.getName());
                    tempNodes.get(4).setText(connBean.getPwd());
                    tempNodes.get(5).setText(connBean.getPath());
                    
                    XMLWriter writer = new XMLWriter(new FileOutputStream(XML_PATH));
                    writer.write(document);
                    writer.close();
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error found when updating connection info.");
            String exceptionMessage = ExceptionStacktraceHandler.stackTraceToString(e);
            Logger.getLogger().error("Exception found \n" + exceptionMessage);
        }
    }
}
