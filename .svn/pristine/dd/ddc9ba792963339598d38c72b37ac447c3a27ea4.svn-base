package com.sys.common.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    /**
     * AES算法加密明文
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String encryptAES(String data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(ResizeBytesArray(SysConst.Encryption4NetKEY.getBytes(),32), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(ResizeBytesArray(SysConst.Encryption4NetIV.getBytes(),16));
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return Base64Util.encode(encrypted).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES算法解密密文
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String decryptAES(String data) throws Exception {
        try
        {
            byte[] encrypted1 = Base64Util.decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyspec = new SecretKeySpec(ResizeBytesArray(SysConst.Encryption4NetKEY.getBytes(),32), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(ResizeBytesArray(SysConst.Encryption4NetIV.getBytes(),16));
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] ResizeBytesArray(byte[] bytes, int newSize)
    {
        byte[] array = new byte[newSize];
        if (bytes.length <= newSize)
        {
            for (int i = 0; i < bytes.length; i++)
            {
                array[i] = bytes[i];
            }
        }
        else
        {
            int num = 0;
            for (int i = 0; i < bytes.length; i++)
            {
                byte[] arg_40_0 = array;
                int expr_3C = num++;
                arg_40_0[expr_3C] ^= bytes[i];
                if (num >= array.length)
                {
                    num = 0;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) throws Exception {
        //加密测试
        String encryptAEStest=encryptAES("99");
        System.out.println(encryptAEStest);

        //解密测试
        String decryptAEStest=decryptAES("Z4PrR6qH7fZlb0E/N7GZbg==");
        System.out.println(decryptAEStest);
    }
}
