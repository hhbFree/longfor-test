package aka;

/**
 * AES-Rijndael算法实现<br>
 * @author lichunlei
 *
 */
public class Rijndael_Algorithms {
	
	private byte[] key;
	
	/**-------------------- Rijndael round subkeys ---------------------*/
	private byte roundKeys[][][] = new byte[11][4][4];
	
	/**
	*  Rijndael key schedule function.  Takes 16-byte key and creates <br>
	 *  all Rijndael's internal subkeys ready for encryption. <br>
	 */
	public Rijndael_Algorithms(byte[] key) {
		this.key = key;
		RijndaelKeySchedule();
	}
	
	private void RijndaelKeySchedule()
	{
		byte roundConst;
		int i, j;

		// first round key equals key
		for (i = 0; i < 16; i++) {
			roundKeys[0][i & 0x03][i >> 2] = key[i];
		}
		roundConst = 1;
		// now calculate round keys */
		for (i = 1; i < 11; i++) {
			roundKeys[i][0][0] = (byte) (S[byteToU8(roundKeys[i - 1][1][3])]
					^ roundKeys[i - 1][0][0] ^ roundConst);
			roundKeys[i][1][0] = (byte) (S[byteToU8(roundKeys[i - 1][2][3])] ^ roundKeys[i - 1][1][0]);
			roundKeys[i][2][0] = (byte) (S[byteToU8(roundKeys[i - 1][3][3])] ^ roundKeys[i - 1][2][0]);
			roundKeys[i][3][0] = (byte) (S[byteToU8(roundKeys[i - 1][0][3])] ^ roundKeys[i - 1][3][0]);
			for (j = 0; j < 4; j++) {
				roundKeys[i][j][1] = (byte) (roundKeys[i - 1][j][1] ^ roundKeys[i][j][0]);
				roundKeys[i][j][2] = (byte) (roundKeys[i - 1][j][2] ^ roundKeys[i][j][1]);
				roundKeys[i][j][3] = (byte) (roundKeys[i - 1][j][3] ^ roundKeys[i][j][2]);
			}
			// update round constant */
			roundConst = Xtime[byteToU8(roundConst)];
		}
	}
	
	/**
	 *  Rijndael encryption function.  Takes 16-byte input and creates  
	 *  16-byte output (using round keys already derived from 16-byte  
	 *  key). <br>
	 */
	public byte[] rijndaelEncrypt(byte[] input) {
		byte[][] state = new byte[4][4];
		int i, r;

		// initialise state array from input byte string
		for (i = 0; i < 16; i++) {
			state[i & 0x3][i >> 2] = input[i];
		}
		// add first round_key
		keyAdd(state, roundKeys, 0);
		// do lots of full rounds
		for (r = 1; r <= 9; r++) {
			byteSub(state);
			shiftRow(state);
			mixColumn(state);
			keyAdd(state, roundKeys, r);
		}
		// final round
		byteSub(state);
		shiftRow(state);
		keyAdd(state, roundKeys, r);
		// produce output byte string from state array
		byte[] output = new byte[16];
		for (i = 0; i < 16; i++) {
			output[i] = state[i & 0x3][i >> 2];
		}

		return output;
	} 
	/**
	 *  Rijndael decryption function.  Takes 16-byte input and creates  
	 *  16-byte output (using round keys already derived from 16-byte  
	 *  key). 
	 */
	public byte[] rijndaelDecrypt(byte[] input)
	{
		//TODO
		return null;
	}

	/**
	 *  Round key addition function 
	 *  @param state[4][4]
	 *  @param roundKeys[11][4][4]
	 *  @param int
	 */
	private void keyAdd(byte[][] state, byte[][][] roundKeys, int round)
	{
		int i, j;
		for (i=0; i<4; i++)
			for (j=0; j<4; j++)
				state[i][j] ^= roundKeys[round][i][j];
		return;
	}

	
	/**
	 *  Byte substitution transformation 
	 *  @param state[4][4]
	 */
	private void byteSub(byte[][] state)
	{
		int i, j;
		for (i=0; i<4; i++)
			for (j=0; j<4; j++)
				state[i][j] = S[byteToU8(state[i][j])];
	}
	
//	 /**
//	  * InvSubBytes方法
//	  */
//	 private void InvByteSub(byte[][] state)
//	 {
//		 int i, j;
//			for (i=0; i<4; i++)
//				for (j=0; j<4; j++)
//					state[i][j] = IS[byteToU8(state[i][j])];
//	 }

	
	/**
	 *  Row shift transformation 
	 *  @param state[4][4]
	 *  
	*/
	private void shiftRow(byte[][] state)
	{
		byte temp;
		/* left rotate row 1 by 1 */
		temp = state[1][0];
		state[1][0] = state[1][1];
		state[1][1] = state[1][2];
		state[1][2] = state[1][3];
		state[1][3] = temp;
		/* left rotate row 2 by 2 */
		temp = state[2][0];
		state[2][0] = state[2][2];
		state[2][2] = temp;
		temp = state[2][1];
		state[2][1] = state[2][3];
		state[2][3] = temp;
		/* left rotate row 3 by 3 */
		temp = state[3][0];
		state[3][0] = state[3][3];
		state[3][3] = state[3][2];
		state[3][2] = state[3][1];
		state[3][1] = temp;
		
		return;
	}
//	
//	/**
//	  * 逆行移位
//	  */
//	 private void InvShiftRows(byte[][] state)
//	 {
//		 byte[][] temp = new byte[4][4];
//		 for (int r = 0; r < 4; ++r)
//		 {
//			 for (int c = 0; c < 4; ++c)
//			 {
//				 temp[r][c] = state[r][c];
//			 }
//		 }
//
//		 for (int r = 1; r < 4; ++r)
//		 {
//			 for (int c = 0; c < 4; ++c)
//			 {
//				 state[r][c] = temp[r][(c - r + 4) % 4];
//			 }
//		 }
//	 }
	
	/**
	 *  MixColumn transformation
	 *  @param state[4][4]
	 */
	private void mixColumn(byte[][] state)
	{
		byte temp, tmp, tmp0;
		int i;
		/* do one column at a time */
		for (i=0; i<4;i++)
		{
			temp = (byte) (state[0][i] ^ state[1][i] ^ state[2][i] ^ state[3][i]);
			tmp0 = state[0][i];
			/* Xtime array does multiply by x in GF2^8 */
			tmp = Xtime[byteToU8(state[0][i] ^ state[1][i])];
			state[0][i] ^= temp ^ tmp;

			tmp = Xtime[byteToU8(state[1][i] ^ state[2][i])];
			state[1][i] ^= temp ^ tmp;

			tmp = Xtime[byteToU8(state[2][i] ^ state[3][i])];
			state[2][i] ^= temp ^ tmp;

			tmp = Xtime[byteToU8(state[3][i] ^ tmp0)];
			state[3][i] ^= temp ^ tmp;
		}
		
		return;
	}
	
//	/**
//	  * 逆列混合
//	  */
//	 private void InvMixColumns(byte[][] state)
//	 {
//	  byte[][] temp = new byte[4][4];
//	  for (int r = 0; r < 4; ++r)
//	  {
//	   for (int c = 0; c < 4; ++c)
//	   {
//	    temp[r][c] = state[r][c];
//	   }
//	  }
//
//	  for (int c = 0; c < 4; c++)
//	  {
//	   state[0][c] = (byte) (gfmultby0e(temp[0][c])
//	     ^ gfmultby0b(temp[1][c]) ^ gfmultby0d(temp[2][c]) ^ gfmultby09(temp[3][c]));
//	   state[1][c] = (byte) (gfmultby09(temp[0][c])
//	     ^ gfmultby0e(temp[1][c]) ^ gfmultby0b(temp[2][c]) ^ gfmultby0d(temp[3][c]));
//	   state[2][c] = (byte) (gfmultby0d(temp[0][c]) ^ (gfmultby09(temp[1][c])
//	     ^ gfmultby0e(temp[2][c]) ^ gfmultby0b(temp[3][c])));
//	   state[3][c] = (byte) (gfmultby0b(temp[0][c])
//	     ^ gfmultby0d(temp[1][c]) ^ (gfmultby09(temp[2][c]) ^ gfmultby0e(temp[3][c])));
//	  }
//	 }
	
	/**
	 * byte to u8 
	 * java的byte存在小于0的情况，转化为无符号数
	 * @param b
	 * @return
	 */
	private static int byteToU8(int b)
	{
		return b < 0 ? b + 256 : b ;
	}
	
	

	/**--------------------- Rijndael S box table ----------------------*/
	private static byte S[] = { 99, 124, 119, 123, (byte) 242, 107, 111, (byte) 197, 48, 1,
			103, 43, (byte) 254, (byte) 215, (byte) 171, 118, (byte) 202,
			(byte) 130, (byte) 201, 125, (byte) 250, 89, 71, (byte) 240,
			(byte) 173, (byte) 212, (byte) 162, (byte) 175, (byte) 156,
			(byte) 164, 114, (byte) 192, (byte) 183, (byte) 253, (byte) 147,
			38, 54, 63, (byte) 247, (byte) 204, 52, (byte) 165, (byte) 229,
			(byte) 241, 113, (byte) 216, 49, 21, 4, (byte) 199, 35, (byte) 195,
			24, (byte) 150, 5, (byte) 154, 7, 18, (byte) 128, (byte) 226,
			(byte) 235, 39, (byte) 178, 117, 9, (byte) 131, 44, 26, 27, 110,
			90, (byte) 160, 82, 59, (byte) 214, (byte) 179, 41, (byte) 227, 47,
			(byte) 132, 83, (byte) 209, 0, (byte) 237, 32, (byte) 252,
			(byte) 177, 91, 106, (byte) 203, (byte) 190, 57, 74, 76, 88,
			(byte) 207, (byte) 208, (byte) 239, (byte) 170, (byte) 251, 67, 77,
			51, (byte) 133, 69, (byte) 249, 2, 127, 80, 60, (byte) 159,
			(byte) 168, 81, (byte) 163, 64, (byte) 143, (byte) 146, (byte) 157,
			56, (byte) 245, (byte) 188, (byte) 182, (byte) 218, 33, 16,
			(byte) 255, (byte) 243, (byte) 210, (byte) 205, 12, 19, (byte) 236,
			95, (byte) 151, 68, 23, (byte) 196, (byte) 167, 126, 61, 100, 93,
			25, 115, 96, (byte) 129, 79, (byte) 220, 34, 42, (byte) 144,
			(byte) 136, 70, (byte) 238, (byte) 184, 20, (byte) 222, 94, 11,
			(byte) 219, (byte) 224, 50, 58, 10, 73, 6, 36, 92, (byte) 194,
			(byte) 211, (byte) 172, 98, (byte) 145, (byte) 149, (byte) 228,
			121, (byte) 231, (byte) 200, 55, 109, (byte) 141, (byte) 213, 78,
			(byte) 169, 108, 86, (byte) 244, (byte) 234, 101, 122, (byte) 174,
			8, (byte) 186, 120, 37, 46, 28, (byte) 166, (byte) 180, (byte) 198,
			(byte) 232, (byte) 221, 116, 31, 75, (byte) 189, (byte) 139,
			(byte) 138, 112, 62, (byte) 181, 102, 72, 3, (byte) 246, 14, 97,
			53, 87, (byte) 185, (byte) 134, (byte) 193, 29, (byte) 158,
			(byte) 225, (byte) 248, (byte) 152, 17, 105, (byte) 217,
			(byte) 142, (byte) 148, (byte) 155, 30, (byte) 135, (byte) 233,
			(byte) 206, 85, 40, (byte) 223, (byte) 140, (byte) 161, (byte) 137,
			13, (byte) 191, (byte) 230, 66, 104, 65, (byte) 153, 45, 15,
			(byte) 176, 84, (byte) 187, 22, };

	/**------- This array does the multiplication by x in GF(2^8) ------*/
	private static byte Xtime[] = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30,
			32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64,
			66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98,
			100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124,
			126, (byte) 128, (byte) 130, (byte) 132, (byte) 134, (byte) 136,
			(byte) 138, (byte) 140, (byte) 142, (byte) 144, (byte) 146,
			(byte) 148, (byte) 150, (byte) 152, (byte) 154, (byte) 156,
			(byte) 158, (byte) 160, (byte) 162, (byte) 164, (byte) 166,
			(byte) 168, (byte) 170, (byte) 172, (byte) 174, (byte) 176,
			(byte) 178, (byte) 180, (byte) 182, (byte) 184, (byte) 186,
			(byte) 188, (byte) 190, (byte) 192, (byte) 194, (byte) 196,
			(byte) 198, (byte) 200, (byte) 202, (byte) 204, (byte) 206,
			(byte) 208, (byte) 210, (byte) 212, (byte) 214, (byte) 216,
			(byte) 218, (byte) 220, (byte) 222, (byte) 224, (byte) 226,
			(byte) 228, (byte) 230, (byte) 232, (byte) 234, (byte) 236,
			(byte) 238, (byte) 240, (byte) 242, (byte) 244, (byte) 246,
			(byte) 248, (byte) 250, (byte) 252, (byte) 254, 27, 25, 31, 29, 19,
			17, 23, 21, 11, 9, 15, 13, 3, 1, 7, 5, 59, 57, 63, 61, 51, 49, 55,
			53, 43, 41, 47, 45, 35, 33, 39, 37, 91, 89, 95, 93, 83, 81, 87, 85,
			75, 73, 79, 77, 67, 65, 71, 69, 123, 121, 127, 125, 115, 113, 119,
			117, 107, 105, 111, 109, 99, 97, 103, 101, (byte) 155, (byte) 153,
			(byte) 159, (byte) 157, (byte) 147, (byte) 145, (byte) 151,
			(byte) 149, (byte) 139, (byte) 137, (byte) 143, (byte) 141,
			(byte) 131, (byte) 129, (byte) 135, (byte) 133, (byte) 187,
			(byte) 185, (byte) 191, (byte) 189, (byte) 179, (byte) 177,
			(byte) 183, (byte) 181, (byte) 171, (byte) 169, (byte) 175,
			(byte) 173, (byte) 163, (byte) 161, (byte) 167, (byte) 165,
			(byte) 219, (byte) 217, (byte) 223, (byte) 221, (byte) 211,
			(byte) 209, (byte) 215, (byte) 213, (byte) 203, (byte) 201,
			(byte) 207, (byte) 205, (byte) 195, (byte) 193, (byte) 199,
			(byte) 197, (byte) 251, (byte) 249, (byte) 255, (byte) 253,
			(byte) 243, (byte) 241, (byte) 247, (byte) 245, (byte) 235,
			(byte) 233, (byte) 239, (byte) 237, (byte) 227, (byte) 225,
			(byte) 231, (byte) 229 };
	
}
