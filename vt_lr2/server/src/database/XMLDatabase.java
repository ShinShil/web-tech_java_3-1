package database;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class XMLDatabase implements IPhysicalDatabase {
    private String fileName;
    private Class type;

    public XMLDatabase(String fileName, Class type) throws IOException {
        this.fileName = fileName;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        if(reader.readLine() == null) {
            saveToFile(new Element(type.getName() + "s"));
        }
        this.type = type;
    }

    @Override
    public Object[] getRecords() throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, JDOMException {
        Element rootElement = getRootElement();
        List<Object> res = new ArrayList<>();
        for (Element xmlObjectElement: rootElement.getChildren()) {
            res.add(castXmlToObject(xmlObjectElement));
        }
        return res.toArray();
    }

    @Override
    public void add(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException, JDOMException {
        Element rootElement = getRootElement();
        Element newElement = castObjectToXML(obj);
        rootElement.addContent(newElement);
        saveToFile(rootElement);
    }

    @Override
    public void update(Object oldObj, Object newObj) throws IOException, NoSuchFieldException, IllegalAccessException, JDOMException {

    }

    @Override
    public void deleteRecord(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException {

    }

    private Document getDocument() throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        return (Document) builder.build(fileName);
    }

    private Element findElementById(Object obj) throws JDOMException, IOException {
        Element rootElement = getRootElement();
        for (Element xmlObjectElement: rootElement.getChildren()) {

        }
        return null;
    }

    private Element getRootElement() throws JDOMException, IOException {
        return  getDocument().getRootElement();
    }

    private Element castObjectToXML(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Element> xmlTags = new ArrayList<>();
        for (Field field : fields) {
            Element xmlTag = new Element((field.getName()));
            xmlTag.addContent(field.get(obj).toString());
            xmlTags.add(xmlTag);
        }
        Element objectXml = new Element(obj.getClass().getName());
        objectXml.addContent(xmlTags);
        return objectXml;
    }
    
    private Object castXmlToObject(Element xmlElement) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        List<Element> xmlFields = xmlElement.getChildren();
        Constructor<?> ctor = type.getConstructor();
        Object res = ctor.newInstance();
        Field[] fields = type.getDeclaredFields();
        for(int i = 0; i<xmlFields.size(); ++i) {
            Field field = type.getDeclaredField(xmlFields.get(i).getName());
            if(field.getType().getName().equals("int")) {
                res.getClass().getDeclaredField(field.getName()).set(res, Integer.parseInt(xmlFields.get(i).getContent().get(0).getValue()));
            } else if (fields[i].getType().isEnum()) {
                res.getClass().getDeclaredField(field.getName()).set(res, Enum.valueOf((Class<Enum>)fields[i].getType(), xmlFields.get(i).getContent().get(0).getValue()));
            }
            else {
                res.getClass().getDeclaredField(field.getName()).set(res, xmlFields.get(i).getContent().get(0).getValue());
            }
        }
        return res;
    }

    private void saveToFile(Element rootElement) throws IOException {
        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.write("");
        printWriter.close();
        XMLOutputter outer = new XMLOutputter();
        Document document = rootElement.getParent() == null ? new Document(rootElement) : (Document) rootElement.getParent();
        outer.setFormat(Format.getPrettyFormat());
        outer.output(document, new FileWriter(new File(fileName)));
    }
}
