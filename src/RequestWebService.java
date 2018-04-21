/**
 * Created by Administrator on 2017/7/3.
 */
public interface RequestWebService {
    public String RequestDate(String URL,String nameSpace,String methodName,String parmsName1,String parmsValue1,int... s);
    public String RequestDate1(String URL,String nameSpace,String soapAction,String methodName,String... s);
}
