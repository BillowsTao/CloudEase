package Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.beans.TwitterInfo;
import com.qtu404.beans.UserInfo;
import com.qtu404.enetity.Twitter;
import com.qtu404.enetity.User;
import com.qtu404.service.TwitterService;
import com.qtu404.service.UserService;

import java.util.ArrayList;

public class ServiceTest {
    public static void main(String[] args) {
        String returnString = "error";
        ObjectMapper mapper = new ObjectMapper();
        String twitterId = "100001";
        TwitterInfo twitterInfo = TwitterService.getTwitterInfo(Integer.parseInt(twitterId));

    }
}
