package com.westvalley.util;

public final class Base64{
  private static final int BASELENGTH = 255;
  private static final int LOOKUPLENGTH = 64;
  private static final int TWENTYFOURBITGROUP = 24;
  private static final int EIGHTBIT = 8;
  private static final int SIXTEENBIT = 16;
  private static final int FOURBYTE = 4;
  private static final int SIGN = -128;
  private static final byte PAD = 61;
  private static byte[] base64Alphabet = new byte['ÿ'];
  private static byte[] lookUpBase64Alphabet = new byte[64];
  
  static
  {
    for (int i = 0; i < 255; i++) {
      base64Alphabet[i] = -1;
    }
    for (int i = 90; i >= 65; i--) {
      base64Alphabet[i] = ((byte)(i - 65));
    }
    for (int i = 122; i >= 97; i--) {
      base64Alphabet[i] = ((byte)(i - 97 + 26));
    }
    for (int i = 57; i >= 48; i--) {
      base64Alphabet[i] = ((byte)(i - 48 + 52));
    }
    base64Alphabet[43] = 62;
    base64Alphabet[47] = 63;
    for (int i = 0; i <= 25; i++) {
      lookUpBase64Alphabet[i] = ((byte)(65 + i));
    }
    int i = 26;
    for (int j = 0; i <= 51; j++)
    {
      lookUpBase64Alphabet[i] = ((byte)(97 + j));i++;
    }
    i = 52;
    for (int j = 0; i <= 61; j++)
    {
      lookUpBase64Alphabet[i] = ((byte)(48 + j));i++;
    }
    lookUpBase64Alphabet[62] = 43;
    lookUpBase64Alphabet[63] = 47;
  }
  
  public static boolean isBase64(String paramString)
  {
    return isArrayByteBase64(paramString.getBytes());
  }
  
  public static boolean isBase64(byte paramByte)
  {
    return (paramByte == 61) || (base64Alphabet[paramByte] != -1);
  }
  
  public static boolean isArrayByteBase64(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    if (i == 0) {
      return true;
    }
    for (int j = 0; j < i; j++) {
      if (!isBase64(paramArrayOfByte[j])) {
        return false;
      }
    }
    return true;
  }
  
  public static byte[] encode(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length * 8;
    int j = i % 24;
    int k = i / 24;
    byte[] arrayOfByte = null;
    if (j != 0) {
      arrayOfByte = new byte[(k + 1) * 4];
    } else {
      arrayOfByte = new byte[k * 4];
    }
    int m = 0;int n = 0;int i1 = 0;int i2 = 0;int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    int i6 = 0;
    int i7;
    int i8;
    for (i6 = 0; i6 < k; i6++)
    {
      i5 = i6 * 3;
      i1 = paramArrayOfByte[i5];
      i2 = paramArrayOfByte[(i5 + 1)];
      i3 = paramArrayOfByte[(i5 + 2)];
      
      n = (byte)(i2 & 0xF);
      m = (byte)(i1 & 0x3);
      i4 = i6 * 4;
      i7 = (i1 & 0xFFFFFF80) == 0 ? (byte)(i1 >> 2) : (byte)(i1 >> 2 ^ 0xC0);
      i8 = (i2 & 0xFFFFFF80) == 0 ? (byte)(i2 >> 4) : (byte)(i2 >> 4 ^ 0xF0);
      int i9 = (i3 & 0xFFFFFF80) == 0 ? (byte)(i3 >> 6) : (byte)(i3 >> 6 ^ 0xFC);
      arrayOfByte[i4] = lookUpBase64Alphabet[i7];
      
      arrayOfByte[(i4 + 1)] = lookUpBase64Alphabet[(i8 | m << 4)];
      arrayOfByte[(i4 + 2)] = lookUpBase64Alphabet[(n << 2 | i9)];
      arrayOfByte[(i4 + 3)] = lookUpBase64Alphabet[(i3 & 0x3F)];
    }
    i5 = i6 * 3;
    i4 = i6 * 4;
    if (j == 8)
    {
      i1 = paramArrayOfByte[i5];
      m = (byte)(i1 & 0x3);
      
      i7 = (i1 & 0xFFFFFF80) == 0 ? (byte)(i1 >> 2) : (byte)(i1 >> 2 ^ 0xC0);
      arrayOfByte[i4] = lookUpBase64Alphabet[i7];
      arrayOfByte[(i4 + 1)] = lookUpBase64Alphabet[(m << 4)];
      arrayOfByte[(i4 + 2)] = 61;
      arrayOfByte[(i4 + 3)] = 61;
    }
    else if (j == 16)
    {
      i1 = paramArrayOfByte[i5];
      i2 = paramArrayOfByte[(i5 + 1)];
      n = (byte)(i2 & 0xF);
      m = (byte)(i1 & 0x3);
      i7 = (i1 & 0xFFFFFF80) == 0 ? (byte)(i1 >> 2) : (byte)(i1 >> 2 ^ 0xC0);
      i8 = (i2 & 0xFFFFFF80) == 0 ? (byte)(i2 >> 4) : (byte)(i2 >> 4 ^ 0xF0);
      arrayOfByte[i4] = lookUpBase64Alphabet[i7];
      arrayOfByte[(i4 + 1)] = lookUpBase64Alphabet[(i8 | m << 4)];
      arrayOfByte[(i4 + 2)] = lookUpBase64Alphabet[(n << 2)];
      arrayOfByte[(i4 + 3)] = 61;
    }
    return arrayOfByte;
  }
  
  public static byte[] decode(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length == 0) {
      return new byte[0];
    }
    int i = paramArrayOfByte.length / 4;
    byte[] arrayOfByte = null;
    int j = 0;int k = 0;int m = 0;int n = 0;int i1 = 0;int i2 = 0;
    
    int i3 = 0;
    int i4 = 0;
    
    int i5 = paramArrayOfByte.length;
    while (paramArrayOfByte[(i5 - 1)] == 61)
    {
      i5--;
      if (i5 == 0) {
        return new byte[0];
      }
    }
    arrayOfByte = new byte[i5 - i];
    for (i5 = 0; i5 < i; i5++)
    {
      i4 = i5 * 4;
      i1 = paramArrayOfByte[(i4 + 2)];
      i2 = paramArrayOfByte[(i4 + 3)];
      j = base64Alphabet[paramArrayOfByte[i4]];
      k = base64Alphabet[paramArrayOfByte[(i4 + 1)]];
      if ((i1 != 61) && (i2 != 61))
      {
        m = base64Alphabet[i1];
        n = base64Alphabet[i2];
        arrayOfByte[i3] = ((byte)(j << 2 | k >> 4));
        arrayOfByte[(i3 + 1)] = ((byte)((k & 0xF) << 4 | m >> 2 & 0xF));
        arrayOfByte[(i3 + 2)] = ((byte)(m << 6 | n));
      }
      else if (i1 == 61)
      {
        arrayOfByte[i3] = ((byte)(j << 2 | k >> 4));
      }
      else if (i2 == 61)
      {
        m = base64Alphabet[i1];
        arrayOfByte[i3] = ((byte)(j << 2 | k >> 4));
        arrayOfByte[(i3 + 1)] = ((byte)((k & 0xF) << 4 | m >> 2 & 0xF));
      }
      i3 += 3;
    }
    return arrayOfByte;
  }
}
