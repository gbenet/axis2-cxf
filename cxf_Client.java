package example;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import javax.xml.namespace.QName;
 
/**
 * @param
 * @author wu.lin
 * @description
 * @create 2016年10月25日 19:51
 * @throws
 */
public class HelloWorld {
  public static void main(String[] argv) {
 
    Service service = new Service();
    String url = "http://localhost:8080/cxf/ws/HelloWorld?wsdl";
 
    try {
      Call call = (Call)service.createCall();
      call.setTargetEndpointAddress(new java.net.URL(url));
      call.setOperationName(new QName("http://wwwcomy.iteye.com/","sayHello"));
      call.addParameter(new QName("userName"),org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
      call.setUseSOAPAction(true);
      call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
      String result = (String)call.invoke(new Object[]{"我是Tom,我成功调用你的webService接口了."});
 
      System.out.println(result);
 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}