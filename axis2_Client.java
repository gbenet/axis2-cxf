package com.axis2Client.client;

import com.axis2Client.doamin.User;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import javax.xml.namespace.QName;
import java.io.Serializable;

/**
 * @Copyright: Copyright (c) 2019
 * @Company: www.baosight.com
 * @program: axis2_client
 * @FileName: axis2Client
 * @description: axis2客户端测试
 * @author: binzhang
 * @create: 2019/05/08 11:08
 * @version:
 */
public class axis2Client implements Serializable{

    public static void main(String[] args) {
        Service service = new Service();
        String url = "http://localhost:8080/axis2/services/webservice";
        User user = new User();
        user.setSex("男");
        user.setAge(28);
        user.setName("章斌");
        user.setLevel("2");


        try {
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(url));
            QName qn = new QName("http://controller.webservice.com","test");
            call.setOperationName(qn);
            call.registerTypeMapping(User.class, qn, new BeanSerializerFactory(User.class, qn),new BeanDeserializerFactory(User.class, qn));
            // 设置入参
            call.addParameter("user", qn, javax.xml.rpc.ParameterMode.IN);
            //call.setUseSOAPAction(true);
            call.setReturnType(XMLType.XSD_STRING);
            //call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
            String result = (String)call.invoke(new Object[]{user});

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
