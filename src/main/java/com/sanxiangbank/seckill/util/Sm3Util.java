package com.sanxiangbank.seckill.util;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.Security;


/**
 * @author WangJing
 * @Description SM3实现工具类
 * @date 2021/11/24 16:10
 */
public class Sm3Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] sm3(byte[] srcData) {
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(hash, 0);
        return hash;
    }

    public static String sm3Hex(byte[] srcData) {
        byte[] hash = sm3(srcData);
        String hexString = Hex.toHexString(hash);
        return hexString;
    }

    public static byte[] hmacSm3(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] hash = new byte[mac.getMacSize()];
        mac.doFinal(hash, 0);
        return hash;
    }

    public static String hmacSm3Hex(byte[] key, byte[] srcData) {
        byte[] hash = hmacSm3(key, srcData);
        String hexString = Hex.toHexString(hash);
        return hexString;
    }

    public static byte[] sm3bc(byte[] srcData) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SM3", "BC");
        byte[] digest = messageDigest.digest(srcData);
        return digest;
    }

    public static String sm3bcHex(byte[] srcData) throws Exception {
        byte[] hash = sm3bc(srcData);
        String hexString = Hex.toHexString(hash);
        return hexString;
    }
}