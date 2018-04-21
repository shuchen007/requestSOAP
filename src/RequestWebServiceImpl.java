
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 *
 * 方法二： 应用document方式调用
 * 用ducument方式应用现对繁琐而灵活。现在用的比较多。因为真正摆脱了我们不想要的耦合
 *
 **/
public class RequestWebServiceImpl implements RequestWebService{
    @Override
    public String RequestDate(String URL,String nameSpace,String methodName,String parmsName1,String parmsValue1,int... s) {
        try {
            Options options = new Options();
//            options.setAction("http://webxml.com.cn/toTraditionalChinese");
            // 指定调用WebService的URL
            EndpointReference endpointReference = new EndpointReference(URL);
            //确定目标服务地址
            options.setTo(endpointReference);
            ServiceClient sender = new ServiceClient();
            sender.setOptions(options);
            // 创建一个OMFactory，下面的namespace、方法与参数均需由它创建
            OMFactory factory = OMAbstractFactory.getOMFactory();
            // 创建命名空间
            OMNamespace namespace = factory.createOMNamespace(nameSpace, "");
            // 创建一个method对象
            OMElement method = factory.createOMElement(methodName, namespace);
            // 参数对数
//            OMElement nameElement = factory.createOMElement(parmsValue1, namespace);
            OMElement nameElement = factory.createOMElement(parmsName1, namespace);
            nameElement.addChild(factory.createOMText(nameElement, parmsValue1));
//            method.addChild(nameElement);
            method.build();
//            options.setSoapVersionURI(org.apache.axiom.soap.SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
//            options.setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
            int x = 0;
            for(int i= 1; i<s.length;i++){
                System.out.println(s[i]);
                 x = x + s[i];
            }
            System.out.println(x);
            // 请求并得到结果
            OMElement result = sender.sendReceive(method);
            OMElement d =result.getFirstElement();
            String results = result.getFirstElement().getText();
//            String results = parseXMLSTRING(result.toString(),methodName);
            return results;
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
        return  null;
    }

    @Override
    public String RequestDate1(String URL, String nameSpace,String soapAction, String methodName, String... s) {
        try {
            // 使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            EndpointReference targetEPR = new EndpointReference(URL);
            Options options = serviceClient.getOptions();
            options.setSoapVersionURI(org.apache.axiom.soap.SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
//            options.setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
            //确定目标服务地址
            options.setTo(targetEPR);
            //确定调用方法
            options.setAction(soapAction);
//            options.setAction("http://tempuri.org/IScyxKtService/CNJSNdbs");

//            QName qname = new QName("http://webxml.com.cn/", "toTraditionalChinese");
            QName qname = new QName(nameSpace, methodName);
            // 指定getPrice方法的参数值
            Object[] parameters;
            if(s.length!=0){
                parameters = s;
            }
            else
             parameters = new Object[] {};
            // 指定getPrice方法返回值的数据类型的Class对象
            Class[] returnTypes = new Class[] { String.class };

            // 调用方法一 传递参数，调用服务，获取服务返回结果集
//            OMElement element = serviceClient.invokeBlocking(qname, parameters);
//            //值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
//            //我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
//            String result = element.getFirstElement().getText();
//            System.out.println("result="+result);

            // 调用方法二 getPrice方法并输出该方法的返回值
            Object[] response = serviceClient.invokeBlocking(qname, parameters, returnTypes);
            // String r = (String) response[0];
            String r = (String) response[0];
//            System.out.println("r="+r);
            return r;

        } catch (AxisFault e) {
            e.printStackTrace();
        }
        return null;
    }

    //解析xml文件，获取其中的返回值
    public static String parseXMLSTRING(String xmlString,String methodName) {
        String returnJson = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            Element root = doc.getDocumentElement();//根节点
            Node node = root.getFirstChild();
            while (!node.getNodeName().equals("4ext")) {
                node = node.getFirstChild();
            }
            if (node.getFirstChild() != null) returnJson = node.getFirstChild().getNodeValue();
            System.out.println("获取的返回参数为：" + returnJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson;
    }
}

