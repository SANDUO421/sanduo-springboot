package com.api.common.utils;

import com.google.common.base.Throwables;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HashUtils {
  

  private static final HashFunction FUNCTION = Hashing.md5();
  
  private static final HashFunction MURMUR_FUNC = Hashing.murmur3_128();
  
  private static final String  SALT = "ruanmou.com";
  
  public static String encryPassword(String password){
    HashCode code = FUNCTION.hashString(password+SALT, Charset.forName("UTF-8"));
    return code.toString();
  }

  public static String hashString(String input){
    HashCode code = null;
      code = MURMUR_FUNC.hashBytes(input.getBytes(StandardCharsets.UTF_8));
      return code.toString();
  }
  
  //public static void main(String[] args) {
  //  System.out.println(encryPassword("session_secret"));
  //}
}
