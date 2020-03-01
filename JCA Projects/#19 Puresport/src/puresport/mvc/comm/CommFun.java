package puresport.mvc.comm;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jfinal.kit.PropKit;

import puresport.constant.EnumStatus;

public final class CommFun {
	
	private static DefaultProfile profile = getProfile();
    private static IAcsClient client = new DefaultAcsClient(profile);
    
    public final static DefaultProfile getProfile() {
    	
    	String secId =  puresport.config.ConfMain.getSecId();
    	String secKey = puresport.config.ConfMain.getSecKey();
    	
    	if(StringUtils.isBlank(secId) || StringUtils.isBlank(secKey)) {
    		return null;
    	}
    	return profile = DefaultProfile.getProfile("cn-hangzhou", secId, secKey);
    	
    }
    
    public static boolean sendPhoneMsg(String phone, String code) {
    	
    	CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "反兴奋剂教育平台");
        request.putQueryParameter("TemplateCode", "SMS_177542518");
        request.putQueryParameter("TemplateParam", String.format("{\"code\":\"%s\"}", code));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }

    	return true;
    }
    
	public static JSONObject resJsonFail(String msg) {
		JSONObject json = new JSONObject();
		json.put("flag", false);
		json.put("msg", msg);
		return json;
	};
	
	public static JSONObject resJsonFail(EnumStatus status) {
		if (null == status) {
			return resJsonFail(StringUtils.EMPTY);
		}
		return resJsonFail(status.getName());
	};
	
	public static String sqlWhereIn(List list) {
		if (CollectionUtils.isEmpty(list)) {
			return "()";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < list.size(); i++) {
			if (i==0) {
				sb.append(" ?");
			} else {
				sb.append(",?");
			}
		}
		sb.append(")");
		return sb.toString();
	}

}
