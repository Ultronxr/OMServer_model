import utils.GetCurrentTime;
import utils.MD5Hash;
import utils.OMConfig;
import utils.RandomNumber;

import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) {

        try {
            //System.out.println(Class.class.getClass().getResource("/").getFile().toString());
            //System.out.println(System.currentTimeMillis());
            //System.out.println(String.valueOf(System.currentTimeMillis()));
            //System.out.println(RandomNumber.simpleStringRandNum());
            String t = OMConfig.getApiPwdReceive()+RandomNumber.simpleStringRandNum()+GetCurrentTime.sysTimestamp();
            System.out.println(MD5Hash.stringToMd5LowerCase(t));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}