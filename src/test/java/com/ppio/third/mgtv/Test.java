package com.ppio.third.mgtv;

public class Test {
    public static void main(String[] args) {


        String s = "\"60.10.128.138\"`\"-\"`\"12/Sep/2021:23:59:50 +0800\"`\"GET /c1/2020/06/19_0/FE7E4EFFBE5240749F9C12940A55F038_20200619_1_1_896_mp4/3C0495F6361E03A0BE3D7238C2BDD4BD_100080_106960_1213_v02_mp4.ts?vcdn=0&drmFlag=0&pno=1100&cpno=6l12kd&authalg=hsv1&ruid=3d66510278374200&suuid=7eae122f-b600-45bf-aa27-32b940e6223c&isTrial=0&scid=381&s=62ffcf6bbc303426e437fae4ca00b561&did=hbg9e4%60b24%6043416&r=e8bae8a4991b7c2bf60da0de5a016e6f&fid=859057C072DA2FC03AFBD908C9CFA68A&uuid=3d665102783742008c4f52f406466082&e=1631721555 HTTP/1.1\"`\"200\"`\"1044118\"`\"1043776\"`\"0.502\"`\"-\"`\"-\"`\"-\"`\"hunantv.com\"`\"Yunfan Android 1.0.0.83\"`\"-\"`\"47917290\"`\"1100\"`\"HIT\"`\"2\"`\"1043776\"`\"GET /c1/2020/06/19_0/FE7E4EFFBE5240749F9C12940A55F038_20200619_1_1_896_mp4/3C0495F6361E03A0BE3D7238C2BDD4BD_100080_106960_1213_v02_mp4.ts?pm=Pi9qfM8kOVWiBIY6CY0JZaWFjHvJG5lFox536Fjl53xoexDXzWvylcBytglFG2hNdBQvLkg2snfsZ45D5unn3zXQPF2PRR3L8yuLqItTMFnmBZBeuZhJhJsFav_jEJSOp6yGpDJ5XxyqDhx4SBoVmMswpOfgmG8SV6_4rjJb6YkoxEo8KHQMf0E5UaKQHf_mg1TW_yZWjj5T7wuB0P8egQt8QgMqWovWpSslVeK~Ibu5yq_LzHUWhw--&cpno=6l12kd&ruid=3d66510278374200&suuid=7eae122f-b600-45bf-aa27-32b940e6223c&isTrial=0&scid=381&did=hbg9e4`b24`43416&drmFlag=0&vcdn=0 HTTP/1.1\"`\"0\"`\"0\"`\"cnc-hbtest-168-26.non80.openredcloud.com\"";
        String[] arr = s.split("\"`\"");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " , " + arr[i]);
        }

    }
}
