/**
 * Created by Administrator on 2017/7/3.
 */
public class Test {
    public static String URL = "http://localhost:8080/test/services/HelloWorld?wsdl";
    public static String nameSpace = "";
    public static String soapAction = "";
    public static RequestWebService x = new RequestWebServiceImpl();
    @org.junit.Test
    public void testParams0(){
//        String URL = "http://www.webxml.com.cn/WebServices/TraditionalSimplifiedWebService.asmx?wsdl";
//        String nameSpace = "http://webxml.com.cn/";
//        String methodName = "toTraditionalChinese";
        String methodName = "testParams0";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{});
        System.out.println("xs="+xs);
    }
    @org.junit.Test
    public void testParams1(){
        String methodName = "testParams1";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{"中国"});
        System.out.println(xs);
    }
    @org.junit.Test
    public void testParams2(){
        String methodName = "testParams2";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{"中国","do"});
        System.out.println(xs);
    }
    @org.junit.Test
    public void testParams3(){
        String methodName = "testParams3";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{"zhog","23","f"});
        System.out.println(xs);
    }
    @org.junit.Test
    public void testParams4(){
        String URL = "http://10.89.2.102/ktwcf/ScyxKtService.svc?wsdl";
        String nameSpace = "http://tempuri.org/";
        String methodName = "GetScyxUnit";
        String soapAction = "http://tempuri.org/IScyxKtService/GetScyxUnit";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{});
        System.out.println(xs);
    }
    @org.junit.Test
    public void testParams5(){
        String URL = "http://10.89.2.102/ktwcf/ScyxKtService.svc?wsdl";
        String nameSpace = "http://tempuri.org/" ;
        String methodName = "CNJSNdbs";
        String soapAction = "http://tempuri.org/IScyxKtService/CNJSNdbs";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{"2016-02-12"});
        System.out.println(xs);
    }
    @org.junit.Test
    public void testParams6(){
        String URL = "http://10.89.5.227/YXGLXXXT4_WAP_ReportService/services/YXGLXXXT_Service.asmx?wsdl";
        String nameSpace = "http://localhost/TreeService";
        String methodName = "MethodParameterList";
        String soapAction = "http://localhost/TreeService/MethodParameterList";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{"getTRQXSJHZXQK_YEAR"});
        System.out.println(xs);
    }
    @org.junit.Test
    public void testParams7(){
        String URL = "http://www.webxml.com.cn/WebServices/TraditionalSimplifiedWebService.asmx?wsdl";
        String nameSpace = "http://webxml.com.cn/";
        String methodName = "toTraditionalChinese";
        String soapAction = "http://webxml.com.cn/toTraditionalChinese";
        String xs = x.RequestDate1(URL,nameSpace,soapAction,methodName,new String[]{"中国"});
        System.out.println(xs);
    }
}
