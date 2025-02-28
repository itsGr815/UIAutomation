package com.dFarm.qa.dFarm.util.execution;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import com.dFarm.qa.dFarm.util.reporters.WSCaller;
import com.dFarm.qa.dFarm.util.reporters.WSException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class GetTestData {
    private static final Logger Logger = LoggerFactory.getLogger(GetTestData.class);
    private static final String queryParamGetV1 = "?Region=%s&Type=%s";
    private static final String queryParamRest = "?Region=%s&User=%s";


    public static String getUserID(String typeValue) throws FlexFrameWorkRunTimeException {
        String environment = AllDataHolder.getEnvironment();
        String extraId = null;
        String endPoint;
        try {
            endPoint = ServiceAndSauce.SERVICEENDPOINT.concat(ServiceAndSauce.USERDETAILS.concat(String.format(queryParamGetV1, environment, typeValue)));
            AllDataHolder.getUtil().report(2,
                    "Fetch userId from Test Data Service",
                    "Fetched userid from the service",
                    "Endpoint :" + endPoint);
            HttpResponse res = AllDataHolder.getWsCaller().getRequest(new HashMap<>(), endPoint);
            //checking whether service returned 200 or not , If not , we are exiting the test
            if (res != null){
                if (res.getStatusLine().getStatusCode() == 200){
                    AllDataHolder.setLoginData(EntityUtils.toString(res.getEntity()));
                    JSONObject allData = new JSONObject(AllDataHolder.getLoginData());
                    extraId = allData.get(ServiceAndSauce.RES_USERNAME).toString();
                    // Skipping test if the service returns null or some message with a space in it
                    if (extraId == null || extraId.matches(".*\\s+.*")){
                        extraId = "";
                        AllDataHolder.setUserId("");
                        if (AllDataHolder.getUtil().getStackTrace().contains("BaseTest")){
                            AllDataHolder.setIsLastRun(true);
                        }
                        AllDataHolder.getUtil().report(3,
                                "User Id", "user Id available",
                                "All user id's may be locked by other tests. Service Response: "
                        +extraId
                        + " .check " + ServiceAndSauce.SERVICEENDPOINT
                        + " for the type: "
                        + AllDataHolder.getType());
                    }
                }else if(res.getStatusLine().getStatusCode() == 202){
                    extraId = "";
                    AllDataHolder.setUserId("");
                    if (AllDataHolder.getUtil().getStackTrace().contains("BaseTest")){
                        AllDataHolder.setIsLastRun(true);
                    }
                    AllDataHolder.getUtil().report(3,
                            "userId",
                             "user id available",
                             "All User Ids in use response: " + res.getStatusLine().getStatusCode());
                }else if (res.getStatusLine().getStatusCode() == 503){
                    extraId = "";
                    AllDataHolder.setUserId("");
                    if (AllDataHolder.getUtil().getStackTrace().contains("BaseTest")){
                        AllDataHolder.setIsLastRun(true);
                    }
                    AllDataHolder.getUtil().report(3,
                            "userId",
                            "user id available",
                            "Service is down Response : " + res.getStatusLine().getStatusCode());
                }
            }else {
                extraId = "";
                AllDataHolder.setUserId("");
                if (AllDataHolder.getUtil().getStackTrace().contains("BaseTest")){
                    AllDataHolder.setIsLastRun(true);
                }
                AllDataHolder.getUtil().report(DFarmConstants.WARNING,
                        "userId",
                        "user id available",
                        "NULL object returned by service" + res.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
        return extraId;
    }

    public static void resetUserID(String id) throws FlexFrameWorkRunTimeException {
        String environment = AllDataHolder.getEnvironment();
        try {
            WSCaller wsCaller = new WSCaller();
            HttpResponse httpResponse = wsCaller.getRequest(new HashMap<>(), ServiceAndSauce.SERVICEENDPOINT.concat(ServiceAndSauce.RESETUSER)
                    .concat(String.format(queryParamRest, environment, id)));
            String response = EntityUtils.toString(httpResponse.getEntity());
            // to check if the user id is reset successfully
            if (response.equalsIgnoreCase(id)){
                AllDataHolder.getUtil().report(DFarmConstants.INFO, "User ID", "User Id reset", "user ID:" + id + "reset successfully");
            }else {
                AllDataHolder.getUtil().
                        report(DFarmConstants.SKIP, "User ID", "User Id reset", "Test Data service response:" + response);
            }
        } catch (Exception e) {
           throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static void reset(String id){
        String environment = AllDataHolder.getEnvironment();
        try {
            WSCaller wsCaller = new WSCaller();
            HttpResponse httpResponse = wsCaller.getRequest(new HashMap<>(), ServiceAndSauce.SERVICEENDPOINT.concat(ServiceAndSauce.RESETUSER)
                    .concat(String.format(queryParamRest, environment, id)));
            EntityUtils.toString(httpResponse.getEntity());
        }catch (Exception e){
           Logger.error(
                   "Error resetting inuse id from testdata service. If error persists, turn of centralized reporting"
           );
        }
    }








}
