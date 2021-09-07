package com.ppio.third.mgtv;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class UserPwdGenerator {

//    用户名：test
//    密码：VC4LRAMXpzX3fHECcZ1g


    /**
     * 用户名：ppio
     * <p>
     * 密码：UP32s7y1u#LXN%Kd
     * <p>
     * 服务地址：
     * <p>
     * http://223.112.135.11:20000
     * <p>
     * http://223.112.135.15:20000
     */

//      http://223.112.135.11:20000/download.do?f=access.log.202109011100.gz
    //   http://223.112.135.15/download.do?f=access.log.202108311400.gz


// curl --user ppio:UP32s7y1u#LXN%Kd  "http://223.112.135.15:20000/download.do?f=access.log.202109011300.gz" --output "1.gz"
// curl --user ppio:UP32s7y1u#LXN%Kd  "http://223.112.135.15/download.do?f=access.log.202108311400.gz" --output "2.gz"
//

//    223.112.135.15
//    curl --user ppio:UP32s7y1u#LXN%Kd  "http://223.112.135.15:20000/list.do"

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] b = ("ppio" + ":" + "UP32s7y1u#LXN%Kd").getBytes();
        System.out.println(new BASE64Encoder().encode(b));

    }
}
