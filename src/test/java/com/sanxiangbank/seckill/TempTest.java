package com.sanxiangbank.seckill;

import com.sanxiangbank.seckill.util.Sm3Util;
import org.junit.Test;


public class TempTest {
    @Test
    public void temp() throws Exception {
        String[] passwords = new String[]{"1321321","123213","123123","bnm"};
        for (String password:passwords) {
            String ans = Sm3Util.sm3bcHex(password.getBytes());
            System.out.println(ans.length());
            System.out.println(ans);
        }
    }
}
