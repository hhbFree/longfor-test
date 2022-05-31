package aka;

import javax.xml.bind.SchemaOutputResolver;

public class AlgorithumsUtil {

	/**
	 * 将二进制字符串转换成16进制
	 * 将6字节的byte数组 转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制字符串转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}


	/**
	 * long值转成2字节的byte数组
	 * @param num
	 * @return
	 */
	public static byte[] long2byteArray(long num) {
		byte[] result = new byte[2];
		result[0] = (byte)(num >>> 8);
		result[1] = (byte)(num);
		return result;
	}


	/**
     * long值转成6字节的byte数组 
     * @param num 
     * @return 
     */  
    public static byte[] long6byteArray(long num) {
        byte[] result = new byte[6];  
        result[0] = (byte)(num >>> 40);//取最高8位放到0下标
        result[1] = (byte)(num >>> 32);//取次高8为放到1下标
        result[2] = (byte)(num >>> 24);  //取次低8位放到2下标   
        result[3] = (byte)(num >>> 16);  //取最低8位放到3下标
        result[4] = (byte)(num >>> 8); 
        result[5] = (byte)(num);        
        return result;  
    }




	/**
	 * long值转成16字节的byte数组
	 * @param num
	 * @return
	 */
	public static byte[] long16byteArray(long num) {
		byte[] result = new byte[16];
		result[0] = (byte)(num >>> 120);//取最高8位放到0下标
		result[1] = (byte)(num >>> 112);//取最高8位放到1下标
		result[2] = (byte)(num >>> 104);//取最高8位放到2下标
		result[3] = (byte)(num >>> 96);//取最高8位放到3下标
		result[4] = (byte)(num >>> 88);//取最高8位放到4下标
		result[5] = (byte)(num >>> 80);//取最高8位放到5下标
		result[6] = (byte)(num >>> 72);//取最高8位放到6下标
		result[7] = (byte)(num >>> 64);//取最高8位放到7下标
		result[8] = (byte)(num >>> 56);//取最高8位放到8下标
		result[9] = (byte)(num >>> 48);//取最高8位放到9下标
		result[10] = (byte)(num >>> 40);//取最高8位放到10下标
		result[11] = (byte)(num >>> 32);//取次高8为放到11下标
		result[12] = (byte)(num >>> 24);  //取次低8位放到12下标
		result[13] = (byte)(num >>> 16);  //取最低8位放到13下标
		result[14] = (byte)(num >>> 8);
		result[15] = (byte)(num);
		return result;
	}

	public static long byteArray2Long(byte[] bytes)
    {
    	int mask=0xff;  
        int temp=0;  
        int n=0;  
        for(int i=0;i<bytes.length;i++){  
           n<<=8;  
           temp=bytes[i]&mask;  
           n|=temp;  
       }  
        return n;  
    }
    
    public static void main(String[] args)
    {
    	long l =111;
    	System.out.println(l);
    	//将0转换为6字节的byte数组
    	byte[] bytes = AlgorithumsUtil.long2byteArray(l);
		for (byte aByte : bytes) {

		System.out.println(aByte);
		}

		//将6字节的byte数组 转换成16进制
    	System.out.println(parseByte2HexStr(bytes));




		long l2 = 2;
		byte[] b = new byte[16];
		b = Long.toString(l).getBytes();


    }
	
}
