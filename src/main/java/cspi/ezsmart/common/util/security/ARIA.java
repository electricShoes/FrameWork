 package cspi.ezsmart.common.util.security;
 
 import java.io.PrintStream;
 import java.security.InvalidKeyException;
 import java.util.Date;
 
 public class ARIA
 {
   private static final char[] HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
   
   private static final int[][] KRK = new int[][] { { 1367130551, 656542356, -32265240, -90542368 }, { 1840335564, -1641953248, -14110251, -279059792 }, { -611174627, 556198256, 52729717, 82364686 } };
   
   private static final byte[] S1 = new byte[256];
   private static final byte[] S2 = new byte[256];
   private static final byte[] X1 = new byte[256];
   private static final byte[] X2 = new byte[256];
   
   private static final int[] TS1 = new int[256];
   private static final int[] TS2 = new int[256];
   private static final int[] TX1 = new int[256];
   private static final int[] TX2 = new int[256];
 
   
   static {
     int[] exp = new int[256];
     int[] log = new int[256];
     exp[0] = 1; int i;
     for (i = 1; i < 256; i++) {
       int k = exp[i - 1] << 1 ^ exp[i - 1];
       if ((k & 0x100) != 0) k ^= 0x11B; 
       exp[i] = k;
     } 
     for (i = 1; i < 255; i++) {
       log[exp[i]] = i;
     }
     int[][] A = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 1, 1, 1, 1, 1 } };
     int[][] B = { { 0, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1 }, { 1, 1 }, { 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
     int j;
     for (j = 0; j < 256; j++) {
       int p, t = 0;
       if (j == 0) { p = 0; }
       else { p = exp[255 - log[j]]; }
        for (int k = 0; k < 8; k++) {
         int s = 0;
         for (int m = 0; m < 8; m++) {
           if ((p >>> 7 - m & 0x1) != 0) s ^= A[m][k]; 
         } 
         t = t << 1 ^ s;
       } 
       t ^= 0x63;
       S1[j] = (byte)t;
       X1[t] = (byte)j;
     } 
     for (j = 0; j < 256; j++) {
       int p, t = 0;
       if (j == 0) { p = 0; }
       else { p = exp[247 * log[j] % 255]; }
        for (int k = 0; k < 8; k++) {
         int s = 0;
         for (int m = 0; m < 8; m++) {
           if ((p >>> m & 0x1) != 0) s ^= B[7 - k][m]; 
         } 
         t = t << 1 ^ s;
       } 
       t ^= 0xE2;
       S2[j] = (byte)t;
       X2[t] = (byte)j;
     } 
     
     for (j = 0; j < 256; j++) {
       TS1[j] = 65793 * (S1[j] & 0xFF);
       TS2[j] = 16777473 * (S2[j] & 0xFF);
       TX1[j] = 16842753 * (X1[j] & 0xFF);
       TX2[j] = 16843008 * (X2[j] & 0xFF);
     } 
   }
   
   private int keySize = 0;
   private int numberOfRounds = 0;
   private byte[] masterKey = null;
   private int[] encRoundKeys = null, decRoundKeys = null;
 
   
   public ARIA(int keySize) throws InvalidKeyException {
     setKeySize(keySize);
   }
 
 
 
 
   
   void reset() {
     this.keySize = 0;
     this.numberOfRounds = 0;
     this.masterKey = null;
     this.encRoundKeys = null;
     this.decRoundKeys = null;
   }
 
   
   int getKeySize() {
     return this.keySize;
   }
 
   
   void setKeySize(int keySize) throws InvalidKeyException {
     reset();
     if (keySize != 128 && keySize != 192 && keySize != 256) throw new InvalidKeyException("keySize=" + keySize); 
     this.keySize = keySize;
     switch (keySize) {
       case 128:
         this.numberOfRounds = 12;
         break;
       case 192:
         this.numberOfRounds = 14;
         break;
       case 256:
         this.numberOfRounds = 16;
         break;
     } 
   }
   
   void setKey(byte[] masterKey) throws InvalidKeyException {
     if (masterKey.length * 8 < this.keySize) throw new InvalidKeyException("masterKey size=" + masterKey.length); 
     this.decRoundKeys = null;
     this.encRoundKeys = null;
     this.masterKey = (byte[])masterKey.clone();
   }
 
   
   void setupEncRoundKeys() throws InvalidKeyException {
     if (this.keySize == 0) throw new InvalidKeyException("keySize"); 
     if (this.masterKey == null) throw new InvalidKeyException("masterKey"); 
     if (this.encRoundKeys == null) this.encRoundKeys = new int[4 * (this.numberOfRounds + 1)]; 
     this.decRoundKeys = null;
     doEncKeySetup(this.masterKey, this.encRoundKeys, this.keySize);
   }
 
   
   void setupDecRoundKeys() throws InvalidKeyException {
     if (this.keySize == 0) throw new InvalidKeyException("keySize"); 
     if (this.encRoundKeys == null) { if (this.masterKey == null) throw new InvalidKeyException("masterKey"); 
       setupEncRoundKeys(); }
      this.decRoundKeys = (int[])this.encRoundKeys.clone();
     doDecKeySetup(this.masterKey, this.decRoundKeys, this.keySize);
   }
 
   
   void setupRoundKeys() throws InvalidKeyException {
     setupDecRoundKeys();
   }
 
   
   private static void doCrypt(byte[] i, int ioffset, int[] rk, int nr, byte[] o, int ooffset) {
     int j = 0;
     
     int t0 = toInt(i[0 + ioffset], i[1 + ioffset], i[2 + ioffset], i[3 + ioffset]);
     int t1 = toInt(i[4 + ioffset], i[5 + ioffset], i[6 + ioffset], i[7 + ioffset]);
     int t2 = toInt(i[8 + ioffset], i[9 + ioffset], i[10 + ioffset], i[11 + ioffset]);
     int t3 = toInt(i[12 + ioffset], i[13 + ioffset], i[14 + ioffset], i[15 + ioffset]);
     
     for (int r = 1; r < nr / 2; r++) {
       t0 ^= rk[j++];
       t1 ^= rk[j++];
       t2 ^= rk[j++];
       t3 ^= rk[j++];
       t0 = TS1[t0 >>> 24 & 0xFF] ^ TS2[t0 >>> 16 & 0xFF] ^ TX1[t0 >>> 8 & 0xFF] ^ TX2[t0 & 0xFF];
       t1 = TS1[t1 >>> 24 & 0xFF] ^ TS2[t1 >>> 16 & 0xFF] ^ TX1[t1 >>> 8 & 0xFF] ^ TX2[t1 & 0xFF];
       t2 = TS1[t2 >>> 24 & 0xFF] ^ TS2[t2 >>> 16 & 0xFF] ^ TX1[t2 >>> 8 & 0xFF] ^ TX2[t2 & 0xFF];
       t3 = TS1[t3 >>> 24 & 0xFF] ^ TS2[t3 >>> 16 & 0xFF] ^ TX1[t3 >>> 8 & 0xFF] ^ TX2[t3 & 0xFF];
       t1 ^= t2;
       t2 ^= t3;
       t0 ^= t1;
       t3 ^= t1;
       t2 ^= t0;
       t1 ^= t2;
       t1 = badc(t1);
       t2 = cdab(t2);
       t3 = dcba(t3);
       t1 ^= t2;
       t2 ^= t3;
       t0 ^= t1;
       t3 ^= t1;
       t2 ^= t0;
       t1 ^= t2;
       
       t0 ^= rk[j++];
       t1 ^= rk[j++];
       t2 ^= rk[j++];
       t3 ^= rk[j++];
       t0 = TX1[t0 >>> 24 & 0xFF] ^ TX2[t0 >>> 16 & 0xFF] ^ TS1[t0 >>> 8 & 0xFF] ^ TS2[t0 & 0xFF];
       t1 = TX1[t1 >>> 24 & 0xFF] ^ TX2[t1 >>> 16 & 0xFF] ^ TS1[t1 >>> 8 & 0xFF] ^ TS2[t1 & 0xFF];
       t2 = TX1[t2 >>> 24 & 0xFF] ^ TX2[t2 >>> 16 & 0xFF] ^ TS1[t2 >>> 8 & 0xFF] ^ TS2[t2 & 0xFF];
       t3 = TX1[t3 >>> 24 & 0xFF] ^ TX2[t3 >>> 16 & 0xFF] ^ TS1[t3 >>> 8 & 0xFF] ^ TS2[t3 & 0xFF];
       t1 ^= t2;
       t2 ^= t3;
       t0 ^= t1;
       t3 ^= t1;
       t2 ^= t0;
       t1 ^= t2;
       t3 = badc(t3);
       t0 = cdab(t0);
       t1 = dcba(t1);
       t1 ^= t2;
       t2 ^= t3;
       t0 ^= t1;
       t3 ^= t1;
       t2 ^= t0;
       t1 ^= t2;
     } 
     t0 ^= rk[j++];
     t1 ^= rk[j++];
     t2 ^= rk[j++];
     t3 ^= rk[j++];
     t0 = TS1[t0 >>> 24 & 0xFF] ^ TS2[t0 >>> 16 & 0xFF] ^ TX1[t0 >>> 8 & 0xFF] ^ TX2[t0 & 0xFF];
     t1 = TS1[t1 >>> 24 & 0xFF] ^ TS2[t1 >>> 16 & 0xFF] ^ TX1[t1 >>> 8 & 0xFF] ^ TX2[t1 & 0xFF];
     t2 = TS1[t2 >>> 24 & 0xFF] ^ TS2[t2 >>> 16 & 0xFF] ^ TX1[t2 >>> 8 & 0xFF] ^ TX2[t2 & 0xFF];
     t3 = TS1[t3 >>> 24 & 0xFF] ^ TS2[t3 >>> 16 & 0xFF] ^ TX1[t3 >>> 8 & 0xFF] ^ TX2[t3 & 0xFF];
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     t1 = badc(t1);
     t2 = cdab(t2);
     t3 = dcba(t3);
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     
     t0 ^= rk[j++];
     t1 ^= rk[j++];
     t2 ^= rk[j++];
     t3 ^= rk[j++];
     o[0 + ooffset] = (byte)(X1[0xFF & t0 >>> 24] ^ rk[j] >>> 24);
     o[1 + ooffset] = (byte)(X2[0xFF & t0 >>> 16] ^ rk[j] >>> 16);
     o[2 + ooffset] = (byte)(S1[0xFF & t0 >>> 8] ^ rk[j] >>> 8);
     o[3 + ooffset] = (byte)(S2[0xFF & t0] ^ rk[j]);
     o[4 + ooffset] = (byte)(X1[0xFF & t1 >>> 24] ^ rk[j + 1] >>> 24);
     o[5 + ooffset] = (byte)(X2[0xFF & t1 >>> 16] ^ rk[j + 1] >>> 16);
     o[6 + ooffset] = (byte)(S1[0xFF & t1 >>> 8] ^ rk[j + 1] >>> 8);
     o[7 + ooffset] = (byte)(S2[0xFF & t1] ^ rk[j + 1]);
     o[8 + ooffset] = (byte)(X1[0xFF & t2 >>> 24] ^ rk[j + 2] >>> 24);
     o[9 + ooffset] = (byte)(X2[0xFF & t2 >>> 16] ^ rk[j + 2] >>> 16);
     o[10 + ooffset] = (byte)(S1[0xFF & t2 >>> 8] ^ rk[j + 2] >>> 8);
     o[11 + ooffset] = (byte)(S2[0xFF & t2] ^ rk[j + 2]);
     o[12 + ooffset] = (byte)(X1[0xFF & t3 >>> 24] ^ rk[j + 3] >>> 24);
     o[13 + ooffset] = (byte)(X2[0xFF & t3 >>> 16] ^ rk[j + 3] >>> 16);
     o[14 + ooffset] = (byte)(S1[0xFF & t3 >>> 8] ^ rk[j + 3] >>> 8);
     o[15 + ooffset] = (byte)(S2[0xFF & t3] ^ rk[j + 3]);
   }
 
   
   void encrypt(byte[] i, int ioffset, byte[] o, int ooffset) throws InvalidKeyException {
     if (this.keySize == 0) throw new InvalidKeyException("keySize"); 
     if (this.encRoundKeys == null) { if (this.masterKey == null) throw new InvalidKeyException("masterKey"); 
       setupEncRoundKeys(); }
      doCrypt(i, ioffset, this.encRoundKeys, this.numberOfRounds, o, ooffset);
   }
 
   
   byte[] encrypt(byte[] i, int ioffset) throws InvalidKeyException {
     byte[] o = new byte[16];
     encrypt(i, ioffset, o, 0);
     return o;
   }
 
   
   void decrypt(byte[] i, int ioffset, byte[] o, int ooffset) throws InvalidKeyException {
     if (this.keySize == 0) throw new InvalidKeyException("keySize"); 
     if (this.decRoundKeys == null) { if (this.masterKey == null) throw new InvalidKeyException("masterKey"); 
       setupDecRoundKeys(); }
      doCrypt(i, ioffset, this.decRoundKeys, this.numberOfRounds, o, ooffset);
   }
 
   
   byte[] decrypt(byte[] i, int ioffset) throws InvalidKeyException {
     byte[] o = new byte[16];
     decrypt(i, ioffset, o, 0);
     return o;
   }
 
   
   private static void doEncKeySetup(byte[] mk, int[] rk, int keyBits) {
     int j = 0;
     int[] w0 = new int[4];
     int[] w1 = new int[4];
     int[] w2 = new int[4];
     int[] w3 = new int[4];
     
     w0[0] = toInt(mk[0], mk[1], mk[2], mk[3]);
     w0[1] = toInt(mk[4], mk[5], mk[6], mk[7]);
     w0[2] = toInt(mk[8], mk[9], mk[10], mk[11]);
     w0[3] = toInt(mk[12], mk[13], mk[14], mk[15]);
     
     int q = (keyBits - 128) / 64;
     int t0 = w0[0] ^ KRK[q][0];
     int t1 = w0[1] ^ KRK[q][1];
     int t2 = w0[2] ^ KRK[q][2];
     int t3 = w0[3] ^ KRK[q][3];
     t0 = TS1[t0 >>> 24 & 0xFF] ^ TS2[t0 >>> 16 & 0xFF] ^ TX1[t0 >>> 8 & 0xFF] ^ TX2[t0 & 0xFF];
     t1 = TS1[t1 >>> 24 & 0xFF] ^ TS2[t1 >>> 16 & 0xFF] ^ TX1[t1 >>> 8 & 0xFF] ^ TX2[t1 & 0xFF];
     t2 = TS1[t2 >>> 24 & 0xFF] ^ TS2[t2 >>> 16 & 0xFF] ^ TX1[t2 >>> 8 & 0xFF] ^ TX2[t2 & 0xFF];
     t3 = TS1[t3 >>> 24 & 0xFF] ^ TS2[t3 >>> 16 & 0xFF] ^ TX1[t3 >>> 8 & 0xFF] ^ TX2[t3 & 0xFF];
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     t1 = badc(t1);
     t2 = cdab(t2);
     t3 = dcba(t3);
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     
     if (keyBits > 128) {
       w1[0] = toInt(mk[16], mk[17], mk[18], mk[19]);
       w1[1] = toInt(mk[20], mk[21], mk[22], mk[23]);
       if (keyBits > 192) {
         w1[2] = toInt(mk[24], mk[25], mk[26], mk[27]);
         w1[3] = toInt(mk[28], mk[29], mk[30], mk[31]);
       } else {
         
         w1[3] = 0; w1[2] = 0;
       } 
     } else {
       
       w1[3] = 0; w1[2] = 0; w1[1] = 0; w1[0] = 0;
     } 
     w1[0] = w1[0] ^ t0;
     w1[1] = w1[1] ^ t1;
     w1[2] = w1[2] ^ t2;
     w1[3] = w1[3] ^ t3;
     t0 = w1[0];
     t1 = w1[1];
     t2 = w1[2];
     t3 = w1[3];
     
     q = (q == 2) ? 0 : (q + 1);
     t0 ^= KRK[q][0];
     t1 ^= KRK[q][1];
     t2 ^= KRK[q][2];
     t3 ^= KRK[q][3];
     t0 = TX1[t0 >>> 24 & 0xFF] ^ TX2[t0 >>> 16 & 0xFF] ^ TS1[t0 >>> 8 & 0xFF] ^ TS2[t0 & 0xFF];
     t1 = TX1[t1 >>> 24 & 0xFF] ^ TX2[t1 >>> 16 & 0xFF] ^ TS1[t1 >>> 8 & 0xFF] ^ TS2[t1 & 0xFF];
     t2 = TX1[t2 >>> 24 & 0xFF] ^ TX2[t2 >>> 16 & 0xFF] ^ TS1[t2 >>> 8 & 0xFF] ^ TS2[t2 & 0xFF];
     t3 = TX1[t3 >>> 24 & 0xFF] ^ TX2[t3 >>> 16 & 0xFF] ^ TS1[t3 >>> 8 & 0xFF] ^ TS2[t3 & 0xFF];
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     t3 = badc(t3);
     t0 = cdab(t0);
     t1 = dcba(t1);
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     t0 ^= w0[0];
     t1 ^= w0[1];
     t2 ^= w0[2];
     t3 ^= w0[3];
     w2[0] = t0;
     w2[1] = t1;
     w2[2] = t2;
     w2[3] = t3;
     
     q = (q == 2) ? 0 : (q + 1);
     t0 ^= KRK[q][0];
     t1 ^= KRK[q][1];
     t2 ^= KRK[q][2];
     t3 ^= KRK[q][3];
     t0 = TS1[t0 >>> 24 & 0xFF] ^ TS2[t0 >>> 16 & 0xFF] ^ TX1[t0 >>> 8 & 0xFF] ^ TX2[t0 & 0xFF];
     t1 = TS1[t1 >>> 24 & 0xFF] ^ TS2[t1 >>> 16 & 0xFF] ^ TX1[t1 >>> 8 & 0xFF] ^ TX2[t1 & 0xFF];
     t2 = TS1[t2 >>> 24 & 0xFF] ^ TS2[t2 >>> 16 & 0xFF] ^ TX1[t2 >>> 8 & 0xFF] ^ TX2[t2 & 0xFF];
     t3 = TS1[t3 >>> 24 & 0xFF] ^ TS2[t3 >>> 16 & 0xFF] ^ TX1[t3 >>> 8 & 0xFF] ^ TX2[t3 & 0xFF];
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     t1 = badc(t1);
     t2 = cdab(t2);
     t3 = dcba(t3);
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     w3[0] = t0 ^ w1[0];
     w3[1] = t1 ^ w1[1];
     w3[2] = t2 ^ w1[2];
     w3[3] = t3 ^ w1[3];
     
     gsrk(w0, w1, 19, rk, j);
     j += 4;
     gsrk(w1, w2, 19, rk, j);
     j += 4;
     gsrk(w2, w3, 19, rk, j);
     j += 4;
     gsrk(w3, w0, 19, rk, j);
     j += 4;
     gsrk(w0, w1, 31, rk, j);
     j += 4;
     gsrk(w1, w2, 31, rk, j);
     j += 4;
     gsrk(w2, w3, 31, rk, j);
     j += 4;
     gsrk(w3, w0, 31, rk, j);
     j += 4;
     gsrk(w0, w1, 67, rk, j);
     j += 4;
     gsrk(w1, w2, 67, rk, j);
     j += 4;
     gsrk(w2, w3, 67, rk, j);
     j += 4;
     gsrk(w3, w0, 67, rk, j);
     j += 4;
     gsrk(w0, w1, 97, rk, j);
     j += 4;
     if (keyBits > 128) {
       gsrk(w1, w2, 97, rk, j);
       j += 4;
       gsrk(w2, w3, 97, rk, j);
       j += 4;
     } 
     if (keyBits > 192) {
       gsrk(w3, w0, 97, rk, j);
       j += 4;
       gsrk(w0, w1, 109, rk, j);
     } 
   }
 
 
 
 
 
 
 
 
 
 
 
   
   private static void doDecKeySetup(byte[] mk, int[] rk, int keyBits) {
     int a = 0;
     int[] t = new int[4];
     
     int z = 32 + keyBits / 8;
     swapBlocks(rk, 0, z);
     a += 4;
     z -= 4;
     
     for (; a < z; a += 4, z -= 4)
       swapAndDiffuse(rk, a, z, t); 
     diff(rk, a, t, 0);
     rk[a] = t[0];
     rk[a + 1] = t[1];
     rk[a + 2] = t[2];
     rk[a + 3] = t[3];
   }
 
   
   private static int toInt(byte b0, byte b1, byte b2, byte b3) {
     return (b0 & 0xFF) << 24 ^ (b1 & 0xFF) << 16 ^ (b2 & 0xFF) << 8 ^ b3 & 0xFF;
   }
 
 
 
 
 
 
 
 
 
   
   private static int m(int t) {
     return 65793 * (t >>> 24 & 0xFF) ^ 16777473 * (t >>> 16 & 0xFF) ^ 16842753 * (t >>> 8 & 0xFF) ^ 16843008 * (t & 0xFF);
   }
 
 
 
 
 
 
 
   
   private static final int badc(int t) {
     return t << 8 & 0xFF00FF00 ^ t >>> 8 & 0xFF00FF;
   }
 
   
   private static final int cdab(int t) {
     return t << 16 & 0xFFFF0000 ^ t >>> 16 & 0xFFFF;
   }
 
   
   private static final int dcba(int t) {
     return (t & 0xFF) << 24 ^ (t & 0xFF00) << 8 ^ (t & 0xFF0000) >>> 8 ^ (t & 0xFF000000) >>> 24;
   }
 
   
   private static final void gsrk(int[] x, int[] y, int rot, int[] rk, int offset) {
     int q = 4 - rot / 32, r = rot % 32, s = 32 - r;
     
     rk[offset] = x[0] ^ y[q % 4] >>> r ^ y[(q + 3) % 4] << s;
     rk[offset + 1] = x[1] ^ y[(q + 1) % 4] >>> r ^ y[q % 4] << s;
     rk[offset + 2] = x[2] ^ y[(q + 2) % 4] >>> r ^ y[(q + 1) % 4] << s;
     rk[offset + 3] = x[3] ^ y[(q + 3) % 4] >>> r ^ y[(q + 2) % 4] << s;
   }
 
 
 
   
   private static final void diff(int[] i, int offset1, int[] o, int offset2) {
     int t0 = m(i[offset1]);
     int t1 = m(i[offset1 + 1]);
     int t2 = m(i[offset1 + 2]);
     int t3 = m(i[offset1 + 3]);
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     t1 = badc(t1);
     t2 = cdab(t2);
     t3 = dcba(t3);
     t1 ^= t2;
     t2 ^= t3;
     t0 ^= t1;
     t3 ^= t1;
     t2 ^= t0;
     t1 ^= t2;
     o[offset2] = t0;
     o[offset2 + 1] = t1;
     o[offset2 + 2] = t2;
     o[offset2 + 3] = t3;
   }
 
 
 
   
   private static final void swapBlocks(int[] arr, int offset1, int offset2) {
     for (int i = 0; i < 4; i++) {
       int t = arr[offset1 + i];
       arr[offset1 + i] = arr[offset2 + i];
       arr[offset2 + i] = t;
     } 
   }
 
   
   private static final void swapAndDiffuse(int[] arr, int offset1, int offset2, int[] tmp) {
     diff(arr, offset1, tmp, 0);
     diff(arr, offset2, arr, offset1);
     arr[offset2] = tmp[0];
     arr[offset2 + 1] = tmp[1];
     arr[offset2 + 2] = tmp[2];
     arr[offset2 + 3] = tmp[3];
   }
   
   private static void printBlock(PrintStream out, byte[] b) {
     int i;
     for (i = 0; i < 4; i++)
       byteToHex(out, b[i]); 
     out.print(" ");
     for (i = 4; i < 8; i++)
       byteToHex(out, b[i]); 
     out.print(" ");
     for (i = 8; i < 12; i++)
       byteToHex(out, b[i]); 
     out.print(" ");
     for (i = 12; i < 16; i++) {
       byteToHex(out, b[i]);
     }
   }
 
 
 
 
 
 
 
 
 
 
 
   
   private static void byteToHex(PrintStream out, byte b) {
     char[] buf = { HEX_DIGITS[b >>> 4 & 0xF], HEX_DIGITS[b & 0xF] };
     out.print(new String(buf));
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static void ARIA_test() throws InvalidKeyException {
     byte[] p = new byte[16];
     byte[] c = new byte[16];
     byte[] mk = new byte[32];
     
     boolean flag = false;
     PrintStream out = System.out;
     cspi.ezsmart.common.util.security.ARIA instance = new cspi.ezsmart.common.util.security.ARIA(256);
     int i;
     for (i = 0; i < 32; i++)
       mk[i] = 0; 
     for (i = 0; i < 16; i++) {
       p[i] = 0;
     }
     out.println("BEGIN testing the roundtrip...");
     out.println("For key size of 256 bits, starting with the zero plaintext and the zero key, let's see if we may recover the plaintext by decrypting the encrypted ciphertext.");
     instance.setKey(mk);
     instance.setupRoundKeys();
     
     out.print("plaintext : ");
     printBlock(out, p);
     out.println();
     instance.encrypt(p, 0, c, 0);
     out.print("ciphertext: ");
     printBlock(out, c);
     out.println();
     instance.decrypt(c, 0, p, 0);
     out.print("decrypted : ");
     printBlock(out, p);
     out.println();
     flag = false;
     for (i = 0; i < 16; i++) {
       if (p[i] != 0) flag = true; 
     }  if (flag) { out.println("The result is incorrect!"); }
     else { out.println("Okay.  The result is correct."); }
      out.println("END   testing the roundtrip.\n");
     
     int TEST_NUM = 8388608;
     out.println("BEGIN speed measurement...");
     int j;
     for (j = 0; j < 16; j++)
       mk[j] = (byte)j; 
     out.println("  First, EncKeySetup():");
     out.print("  masterkey: ");
     printBlock(out, mk);
     out.println();
     instance.reset();
     instance.setKeySize(128);
     instance.setKey(mk);
     for (j = 0; j < 1000; j++)
       instance.setupEncRoundKeys(); 
     Date start = new Date();
     for (int k = 0; k < TEST_NUM; k++)
       instance.setupEncRoundKeys(); 
     Date fin = new Date();
     float lapse = (float)(fin.getTime() - start.getTime()) / 1000.0F;
     out.print("  time lapsed: ");
     out.print(lapse);
     out.println(" sec.");
     out.print("  speed      : ");
     out.print((TEST_NUM * 128) / lapse * 1024.0F * 1024.0F);
     out.println(" megabits/sec.\n");
     
     out.println("  Next, Crypt():"); int m;
     for (m = 0; m < 16; m++)
       p[m] = (byte)(m << 4 ^ m); 
     out.print("  plaintext : ");
     printBlock(out, p);
     out.println();
     for (m = 0; m < 1000; m++)
       instance.encrypt(p, 0, c, 0); 
     start = new Date();
     for (m = 0; m < TEST_NUM; m++)
       instance.encrypt(p, 0, c, 0); 
     fin = new Date();
     out.print("  ciphertext: ");
     printBlock(out, c);
     out.println();
     lapse = (float)(fin.getTime() - start.getTime()) / 1000.0F;
     out.print("  time lapsed: ");
     out.print(lapse);
     out.println(" sec.");
     out.print("  speed      : ");
     out.print((TEST_NUM * 128) / lapse * 1024.0F * 1024.0F);
     out.println(" megabits/sec.\n");
     
     out.println("  Finally, DecKeySetup():");
     for (m = 0; m < 1000; m++)
       instance.setupDecRoundKeys(); 
     start = new Date();
     for (m = 0; m < TEST_NUM; m++)
       instance.setupDecRoundKeys(); 
     fin = new Date();
     lapse = (float)(fin.getTime() - start.getTime()) / 1000.0F;
     out.print("  time lapsed: ");
     out.print(lapse);
     out.println(" sec.");
     out.print("  speed      : ");
     out.print((TEST_NUM * 128) / lapse * 1024.0F * 1024.0F);
     out.println(" megabits/sec.");
     out.println("END   speed measurement.");
   }
 }
