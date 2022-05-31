package aka;

import java.io.UnsupportedEncodingException;

/**
 * AKA 算法实现 <br/>
 * 参见文档：3GPP TS 35.206<br/>
 *
 * @author lichunlei
 */
public class AKA_Auth {

    /**
     * a 128-bit subscriber key that is an input to the functions f1, f1*, f2, f3, f4, f5 and f5*.
     */
    private byte[] key;
    /**
     * a 128-bit value derived from OP and K and used within the computation of the functions.
     */
    private byte[] op_c;

    /**
     * authentication management field
     */
    private byte[] amf = new byte[]{0x00, 0x00};

    Rijndael_Algorithms rij;

    /**
     * OP(Operator Variant Algorithm Configuration Field ): 运营商根密钥<br/>
     * OP长16B，是鉴权计算的一个输入参数。一个运营商的所有用户可以使用相同的OP，以区别其他运营商的用户。<br/>
     * HLR/AUC和USIM卡都将保存OP，且应当保证其一致。    <br/>
     */
    private static byte[] OP = new byte[]{0x62, 0x62, 0x62, 0x62, 0x62, 0x62, 0x62,
            0x62, 0x61, 0x61, 0x61, 0x61, 0x61, 0x61, 0x61, 0x61};

//	private static Hashtable<byte[],Rijndael_Algorithms> rijindaelTable = new Hashtable<byte[],Rijndael_Algorithms>();
//	
//	public synchronized static Rijndael_Algorithms getRijndael_Algorithms(byte[] key)
//	{
//		if(!rijindaelTable.contains(key))
//		{
//			Rijndael_Algorithms rij = new Rijndael_Algorithms(key);
//			rijindaelTable.put(key, rij);
//		}
//		return rijindaelTable.get(key);
//			
//	}

    /**
     * @param op OP(Operator Variant Algorithm Configuration Field ): 运营商根密钥
     */
    public static void setOP(byte[] op) {
        OP = op;
    }

    /**
     * 构造函数
     *
     * @param key
     */
    public AKA_Auth(byte[] key) {
        this.key = key;
        rij = new Rijndael_Algorithms(key);
        op_c = computeOPc();
    }

    /**
     * Algorithm f1<br/>
     * <br/>
     * Computes network authentication code MAC-A from key K, random
     * challenge RAND, sequence number SQN and authentication management
     * field AMF.<br/>
     *
     *  key  byte[16]
     * @param rand byte[16]
     * @param sqn  byte[6]
     * @param amf  byte[2]
     * @return MAC-A byte[8]
     */
    public byte[] f1(byte[] rand, byte[] sqn, byte[] amf) {
        byte[] temp;
        byte[] out1;
        byte[] in1 = new byte[16];
        byte[] rijndaelInput = new byte[16];
        byte[] mac_a = new byte[8];
        byte i;
        //rijndaelKeySchedule(key);
        //byte[] op_c = computeOPc(rij);
        for (i = 0; i < 16; i++) {
			rijndaelInput[i] = (byte) (rand[i] ^ op_c[i]);
		}
        //rijndaelEncrypt( rijndaelInput, temp );
        temp = rij.rijndaelEncrypt(rijndaelInput);

        for (i = 0; i < 6; i++) {
            in1[i] = sqn[i];
            in1[i + 8] = sqn[i];
        }
        for (i = 0; i < 2; i++) {
            in1[i + 6] = amf[i];
            in1[i + 14] = amf[i];
        }
        /* XOR op_c and in1, rotate by r1=64, and XOR *
         * on the constant c1 (which is all zeroes)
         */
        for (i = 0; i < 16; i++)
            rijndaelInput[(i + 8) % 16] = (byte) (in1[i] ^ op_c[i]);
        /* XOR on the value temp computed before */
        for (i = 0; i < 16; i++) {
			rijndaelInput[i] ^= temp[i];
		}
        //rijndaelEncrypt( rijndaelInput, out1 );
        out1 = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 16; i++) {
			out1[i] ^= op_c[i];
		}

        for (i = 0; i < 8; i++) {
			mac_a[i] = out1[i];
		}
        return mac_a;

    }


    /**
     * Algorithms f2-f5<br/>
     * <br/>
     * Takes key K and random challenge RAND, and returns response RES,
     * confidentiality key CK, integrity key IK and anonymity key AK.<br/>
     *
     * key  byte[16]
     * @param rand byte[16]
     * @param res  byte[16] 	out
     * @param ck   byte[16] 	out
     * @param ik   byte[16] 	out
     * @param ak   byte[6] 	out
     */
    public void f2345(byte[] rand, byte[] res, byte[] ck, byte[] ik, byte[] ak) {
        byte[] temp;
        byte[] out;
        byte[] rijndaelInput = new byte[16];
        byte i;
        //rijndaelKeySchedule(key);
        //ComputeOPc( op_c );

        for (i = 0; i < 16; i++) {
			rijndaelInput[i] = (byte) (rand[i] ^ op_c[i]);
		}
        temp = rij.rijndaelEncrypt(rijndaelInput);
        /* To obtain output block OUT2: XOR OPc and TEMP,
         *
         * rotate by r2=0, and XOR on the constant c2 (which *
         * is all zeroes except that the last bit is 1).
         */
        for (i = 0; i < 16; i++) {
			rijndaelInput[i] = (byte) (temp[i] ^ op_c[i]);
		}
        rijndaelInput[15] ^= 1;
        out = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 16; i++) {
			out[i] ^= op_c[i];
		}
        for (i = 0; i < 8; i++) {
			res[i] = out[i + 8];
		}
        System.out.println("res:" + AlgorithumsUtil.parseByte2HexStr(res));
        for (i = 0; i < 6; i++) {
			ak[i] = out[i];
		}
        System.out.println("ak:" + AlgorithumsUtil.parseByte2HexStr(ak));
		/* To obtain output block OUT3: XOR OPc and TEMP,
		ETSI
		*
		3GPP TS 35.206 version 11.0.0 Release 11
		20
		* rotate by r3=32, and XOR on the constant c3 (which
		*
		* is all zeroes except that the next to last bit is 1). */
        for (i = 0; i < 16; i++) {
			rijndaelInput[(i + 12) % 16] = (byte) (temp[i] ^ op_c[i]);
		}
        rijndaelInput[15] ^= 2;
        //RijndaelEncrypt( rijndaelInput, out );
        out = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 16; i++) {
			out[i] ^= op_c[i];
		}
        for (i = 0; i < 16; i++) {
			ck[i] = out[i];
		}
        System.out.println("ck:" + AlgorithumsUtil.parseByte2HexStr(ck));
        /* To obtain output block OUT4: XOR OPc and TEMP,
         *
         * rotate by r4=64, and XOR on the constant c4 (which
         *
         * is all zeroes except that the 2nd from last bit is 1). */
        for (i = 0; i < 16; i++) {
			rijndaelInput[(i + 8) % 16] = (byte) (temp[i] ^ op_c[i]);
		}
        rijndaelInput[15] ^= 4;
        //RijndaelEncrypt( rijndaelInput, out );
        out = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 16; i++) {
			out[i] ^= op_c[i];
		}
        for (i = 0; i < 16; i++) {
			ik[i] = out[i];
		}
        System.out.println("ik:" + AlgorithumsUtil.parseByte2HexStr(ik));
        return;
    }


    /**
     * <br/>
     * Algorithm f1*<br/>
     * <br/>
     * Computes resynch authentication code MAC-S from key K, random
     * challenge RAND, sequence number SQN and authentication management
     * field AMF.<br/>
     * <br/>
     *
     * key  byte[16]
     * @param rand byte[16]
     * @param sqn  byte[6]
     * @param amf  byte[2]
     * @return MAC-S byte[8]
     */
    public byte[] f1star(byte[] rand, byte[] sqn, byte[] amf) {
        byte[] temp;
        byte[] out1;
        byte[] in1 = new byte[16];
        byte[] rijndaelInput = new byte[16];
        byte[] mac_s = new byte[8];
        byte i;
//		RijndaelKeySchedule( k );
//		ComputeOPc( op_c );
        for (i = 0; i < 16; i++) {
			rijndaelInput[i] = (byte) (rand[i] ^ op_c[i]);
		}
        //RijndaelEncrypt( rijndaelInput, temp );
        temp = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 6; i++) {
            in1[i] = sqn[i];
            in1[i + 8] = sqn[i];
        }
        for (i = 0; i < 2; i++) {
            in1[i + 6] = amf[i];
            in1[i + 14] = amf[i];
        }
        /* XOR op_c and in1, rotate by r1=64, and XOR *
         * on the constant c1 (which is all zeroes)
         */
        for (i = 0; i < 16; i++)
            rijndaelInput[(i + 8) % 16] = (byte) (in1[i] ^ op_c[i]);
        /* XOR on the value temp computed before */
        for (i = 0; i < 16; i++) {
			rijndaelInput[i] ^= temp[i];
		}
        //RijndaelEncrypt( rijndaelInput, out1 );
        out1 = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 16; i++) {
			out1[i] ^= op_c[i];
		}
        for (i = 0; i < 8; i++) {
			mac_s[i] = out1[i + 8];
		}
        return mac_s;

    }

    /**
     * Algorithm f5*<br/>
     * <br/>
     * Takes key K and random challenge RAND, and returns resynch
     * anonymity key AK.<br/>
     *
     *  key  byte[16]
     * @param rand byte[16]
     * @return ak byte[6]
     */

    public byte[] f5star(byte[] rand) {
        byte[] temp;
        byte[] out;
        byte[] rijndaelInput = new byte[16];
        byte[] ak = new byte[6];
        byte i;
        //RijndaelKeySchedule( k );
        //ComputeOPc( op_c );
        for (i = 0; i < 16; i++) {
			rijndaelInput[i] = (byte) (rand[i] ^ op_c[i]);
		}
        //RijndaelEncrypt( rijndaelInput, temp );
        temp = rij.rijndaelEncrypt(rijndaelInput);
        /* To obtain output block OUT5: XOR OPc and TEMP,
         *
         * rotate by r5=96, and XOR on the constant c5 (which
         *
         * is all zeroes except that the 3rd from last bit is 1). */
        for (i = 0; i < 16; i++) {
			rijndaelInput[(i + 4) % 16] = (byte) (temp[i] ^ op_c[i]);
		}
        rijndaelInput[15] ^= 8;
        //RijndaelEncrypt( rijndaelInput, out );
        out = rij.rijndaelEncrypt(rijndaelInput);
        for (i = 0; i < 16; i++) {
			out[i] ^= op_c[i];
		}
        for (i = 0; i < 6; i++) {
			ak[i] = out[i];
		}
        return ak;

    }


    public byte[] getAmf() {
        return amf;
    }

    public void setAmf(byte[] amf) {
        this.amf = amf;
    }

    /**
     * computeOPc
     *
     * @return op_c byte[16]
     */
    private byte[] computeOPc() {
        byte i;
        byte[] op_c = rij.rijndaelEncrypt(OP);
        for (i = 0; i < 16; i++) {
			op_c[i] ^= OP[i];
		}
        return op_c;

    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }
	
	/*
	 *
	 * AK:	a 48-bit anonymity key that is the output of either of the functions f5 and f5*.
	 * AMF:	a 16-bit authentication management field that is an input to the functions f1 and f1*.
	 * 		128-bit constants, which are XORed onto intermediate variables.
			a 128-bit confidentiality key that is the output of the function f3.
			a 128-bit integrity key that is the output of the function f4.
			a 128-bit value constructed from SQN and AMF and used in the computation of the functions f1
and f1*.
	 * K:	a 128-bit subscriber key that is an input to the functions f1, f1*, f2, f3, f4, f5 and f5*.
	 * MAC-A:a 64-bit network authentication code that is the output of the function f1.
	 * MAC-S:a 64-bit resynchronisation authentication code that is the output of the function f1*.
	 * OP:	a 128-bit Operator Variant Algorithm Configuration Field that is a component of the functions f1,
f1*, f2, f3, f4, f5 and f5*.
	 * OPC:	a 128-bit value derived from OP and K and used within the computation of the functions.
	 * OUT1,OUT2,OUT3,OUT4,OUT5:
			128-bit computed values from which the outputs of the functions f1, f1*, f2, f3, f4, f5 and f5* are
obtained.
	 * r1,r2,r3,r4,r5:
			integers in the range 0–127 inclusive, which define amounts by which intermediate variables are
cyclically rotated.
	 * RAND:	a 128-bit random challenge that is an input to the functions f1, f1*, f2, f3, f4, f5 and f5*.
	 * RES:	a 64-bit signed response that is the output of the function f2.
	 * SQN:	a 48-bit sequence number that is an input to either of the functions f1 and f1*. (For f1* this input
is more precisely called SQNMS.)
	 * TEMP:	a 128-bit value used within the computation of the functions.

	 */

    /**
     * rand
     * 随机数
     *
     * @param args SQN	48	序列号 序列号一次递增
     *             <p>
     *             AMF	16	鉴权管理域
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
//			* @param key byte[16]
//	* @param rand byte[16]
//	* @param sqn byte[6]
//	* @param amf byte[2]
//	* @return MAC-A byte[8]
        //SQN  1  序列号
        long sqn = 1;
        byte[] sqnb = AlgorithumsUtil.long6byteArray(sqn);
		//rand
        long rand = 2;
        byte[] randb = AlgorithumsUtil.long16byteArray(rand);
		//amf
        long amf = 3;
        byte[] amfb = AlgorithumsUtil.long2byteArray(amf);


        //初始化构造key
        long key = 4;
        //将key转换为16字节的byte数组
        byte[] bytes = AlgorithumsUtil.long16byteArray(key);
        AKA_Auth aka_auth = new AKA_Auth(bytes);

        byte[] macb = aka_auth.f1(randb, sqnb, amfb);
        //System.out.println(macb);
        System.out.println("mac:" + AlgorithumsUtil.parseByte2HexStr(macb));

//		@param key byte[16]
//	* @param rand byte[16]
//	* @param res byte[16] 	out
//	* @param ck byte[16] 	out
//	* @param ik byte[16] 	out
//	* @param ak byte[6] 	out

        byte[] res = new byte[16];
        byte[] ck = new byte[16];
        byte[] ik = new byte[16];
        byte[] ak = new byte[6];
        aka_auth.f2345(randb, res, ck, ik, ak);


//		AUTH = SQN@AK || AMF || MAC
//		AV = AUTH||XRES||CK||IK

    }


}
