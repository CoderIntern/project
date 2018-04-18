package com.daily.dailytest.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

/**
 * XML 工具类
 * <p> Date             :2018/1/18 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public final class JAXBUtil {

    private static final Logger logger = LoggerFactory.getLogger(JAXBUtil.class);

    private JAXBUtil() {

    }

    private static JAXBContext context;

    /**
     * XML转换为JavaBean
     * @param cls
     * @param xml
     * @return
     */
    public static Object xmlToBean(Class<?> cls, String xml) {
        Object bean = null;
        try {
            context = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            logger.error("JAXB Exception...", e);
        }
        return bean;
    }

    /**
     * JavaBean转换为xml
     * @param bean
     * @return
     */
    public static String beanToXml(Object bean) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            context = JAXBContext.newInstance(bean.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, bean.getClass());
            marshaller.marshal(bean, byteArrayOutputStream);
            return new String(byteArrayOutputStream.toByteArray());
        } catch (JAXBException e) {
            logger.error("JAXB Exception...", e);
        }
        return null;
    }
}
