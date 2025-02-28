package com.dFarm.qa.dFarm.constants;

import com.dFarm.qa.dFarm.initializer.ConfigInitializer;
import org.json.HTTP;

public interface ServiceAndSauce {


    String TYPE = "Type";
     String RES_INUSED = "InUseId";
    String PLANKEY  = "plankey";
    String BROWSER = "browser";

    String ISSAUCE = "isSauce";
    String PLATFORM = "platform";
    String SERVICEENDPOINTPATH = "/GetTestData/api/";
    String HTTP = "http://";
    String SERVICEENDPOINT = HTTP + ConfigInitializer.testDataSvcServer + SERVICEENDPOINTPATH;
    String USERDETAILS = "UserDetails";
    String SERVER = "SERVER";
    String RESETUSER = "ResetUser";
    String USERNAME = "Username";
    String RES_USERNAME = "username";
    String RES_INUSEID = "InUseId";
    String ISDEBUG = "isDebug";


}
