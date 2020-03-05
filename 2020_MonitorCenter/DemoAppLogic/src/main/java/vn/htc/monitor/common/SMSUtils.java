package vn.htc.monitor.common;


import com.gk.htc.ahp.brand.jsmpp.bean.Alphabet;
import com.gk.htc.ahp.brand.jsmpp.bean.ESMClass;
import com.gk.htc.ahp.brand.jsmpp.bean.GSMSpecificFeature;
import com.gk.htc.ahp.brand.jsmpp.bean.MessageClass;
import com.gk.htc.ahp.brand.jsmpp.bean.MessageMode;
import com.gk.htc.ahp.brand.jsmpp.bean.MessageType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class SMSUtils {

    static final Logger logger = Logger.getLogger(SMSUtils.class);
    private static final String HEXINDEX = "0123456789abcdef          ABCDEF";
    public static final String PARTNER_NAME = "AHP";
    public static String CHAR_PARSE_MT = "#@#";
    public static final String GSM_CHARACTERS_REGEX = "^[A-Za-z0-9 \\r\\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039EÆæßÉ!\"#$%&'()*+,\\-./:;<=>?¡ÄÖÑÜ§¿äöñüà^{}\\\\\\[~\\]|\u20AC]*$";
    public static int REJECT_MSG_LENG = -1;

    //---------
    public static void main(String[] args) throws IOException {
//        String str = "cong hoa xa hoi chu nghia viet nam, Doc lap tu do mieng nao to thi gap thu mot mieng neu an duoc thi an khong an duoc thi cho cho co duoc khong ha cac che thic]";
//        System.out.println(countLengthNew(str));
        String phone = "841238709119";
        System.out.println(convert11To10(phone));
    }
    
    public static boolean checkTempContent(String content, String temp){
        boolean result = false;
        String contentP = content;
        String tempP = temp;
        if(tempP.trim().equals("")){
            return result;
        }
        //Ho tro toi da 10 tham so param voi key la #param{n}
        String[] patitionsTemp = tempP.split("#param1|#param2|#param3|#param4|#param5|#param6|#param7|#param8|#param9|#param10");
        int[] indexKey = new int[patitionsTemp.length]; 
        for(int i=0;i<patitionsTemp.length;i++){
            if(i==0){
                indexKey[i] = contentP.indexOf(patitionsTemp[i], 0);
            }else{
                indexKey[i] = contentP.indexOf(patitionsTemp[i], indexKey[i-1]);
            }
            //System.out.println("Part = " + indexKey[i] + " with String = " + patitionsTemp[i]);
        }
        
        if(patitionsTemp==null || patitionsTemp.length==0){
            return true;
        }
        
        for(int i=0;i<patitionsTemp.length;i++){
            //Kiem tra co phai tu khoa cuoi khong
            if(i==patitionsTemp.length-1){//Tu khoa cuoi roi
                //Thu hien replace tu khoa nay
                String contentPtemp = contentP.replace(patitionsTemp[i], "");
                if(contentP.equals(contentPtemp)){
                    return false;
                }else{
                    return true;
                }
            }else{//Khong phai tu khoa cuoi
                //Tim tu khoa hien tai
                int indexCurrentKey = contentP.indexOf(patitionsTemp[i]);
                //Neu khong tim thay thi return false luon
                if(indexCurrentKey<0){
                    return false;
                }
                //Thu hien replace tu khoa nay
                String contentPtemp = contentP.replace(patitionsTemp[i], "");
                //Tim den tu khoa tiep theo
                int indexKeyNext = contentP.indexOf(patitionsTemp[i+1]);
                //Neu khong tim thay thi return false luon
                if(indexKeyNext<0){
                    return false;
                }
                //Xoa cac ky tu dang truoc tu khoa nay
                contentPtemp = contentP.substring(indexKeyNext);
                contentP = contentPtemp;
            }
            
            //System.out.println("Sau buoc " + i + " co chuoi = " + contentP);
            
        }
        return result;
    }
    
    
    public static String[] toObjectParamWithTemp(String content, String temp){
        boolean validTemp = checkTempContent(content,temp);
        if(!validTemp){
            return null;
        }
        String[] results;
        
        String tempP = temp;
        String[] patitionsTemp = tempP.split("#param1|#param2|#param3|#param4|#param5|#param6|#param7|#param8|#param9|#param10");
        
        String regexString = "abcdefcohtc";
        for(int i=0; i< patitionsTemp.length; i++){
            if(!patitionsTemp[i].equals("")){
                regexString = regexString+"|"+patitionsTemp[i];
            }
        }
        regexString = regexString.replace("abcdefcohtc|","");
        //System.out.println("Gia tri regex="+regexString);
        
        results = content.split(regexString);
        return results;
    }

    /*-
 * ^[A-Za-z0-9 \r\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039EÆæßÉ!"#$%&amp;'()*+,\-./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}\\\[~\]|\u20AC]*$
 *
 * Assert position at the beginning of the string «^»
 * Match a single character present in the list below «[A-Za-z0-9 \r\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039EÆæßÉ!"#$%&amp;'()*+,\-./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}\\\[~\]|\u20AC]*»
 *    Between zero and unlimited times, as many times as possible, giving back as needed (greedy) «*»
 *    A character in the range between "A" and "Z" «A-Z»
 *    A character in the range between "a" and "z" «a-z»
 *    A character in the range between "0" and "9" «0-9»
 *    The character " " « »
 *    A carriage return character «\r»
 *    A line feed character «\n»
 *    One of the characters "@£$¥èéùìòÇØøÅå" «@£$¥èéùìòÇØøÅå»
 *    Unicode character U+0394 «\u0394», Greek capital Delta
 *    The character "_" «_»
 *    Unicode character U+03A6 «\u03A6», Greek capital Phi
 *    Unicode character U+0393 «\u0393», Greek capital Gamma
 *    Unicode character U+039B «\u039B», Greek capital Lambda
 *    Unicode character U+03A9 «\u03A9», Greek capital Omega
 *    Unicode character U+03A0 «\u03A0», Greek capital Pi
 *    Unicode character U+03A8 «\u03A8», Greek capital Psi
 *    Unicode character U+03A3 «\u03A3», Greek capital Sigma
 *    Unicode character U+0398 «\u0398», Greek capital Theta
 *    Unicode character U+039E «\u039E», Greek capital Xi
 *    One of the characters "ÆæßÉ!"#$%&amp;'()*+," «ÆæßÉ!"#$%&amp;'()*+,»
 *    A - character «\-»
 *    One of the characters "./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}" «./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}»
 *    A \ character «\\»
 *    A [ character «\[»
 *    The character "~" «~»
 *    A ] character «\]»
 *    The character "|" «|»
 *    Unicode character U+20AC «\u20AC», Euro sign
 * Assert position at the end of the string (or before the line break at the end of the string, if any) «$»
     */
    /**
     * gsm =
     * "@£$¥èéùìòÇØøÅåΔ_ΦΓΛΩΠΨΣΘΞ^{}\[~]|€ÆæßÉ!\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";
     * var letter = 'a'; var letterInAlfabet = gsm.indexOf(letter) !== -1;
     *
     *
     *
     */
//     function isGSMAlphabet(text) {
//    var regexp = new RegExp("^[A-Za-z0-9 \\r\\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039EÆæßÉ!\"#$%&'()*+,\\-./:;<=>?¡ÄÖÑÜ§¿äöñüà^{}\\\\\\[~\\]|\u20AC]*$");
//    return regexp.test(text);
//        }
//    String GSM = "@£$¥èéùìòÇØøÅåΔ_ΦΓΛΩΠΨΣΘΞ^{}\[~]|€ÆæßÉ!\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà"; // Javascript
    private static String removeNonUtf8CompliantCharacters(final String inString) {
        if (null == inString) {
            return null;
        }
        byte[] byteArr = inString.getBytes();
        for (int i = 0; i < byteArr.length; i++) {
            byte ch = byteArr[i];
            // remove any characters outside the valid UTF-8 range as well as all control characters
            // except tabs and new lines
            if (!((ch > 31 && ch < 253) || ch == '\t' || ch == '\n' || ch == '\r')) {
                byteArr[i] = ' ';
            }
        }
        return new String(byteArr);
    }

    

    public static boolean isUnicode(String input) {
        boolean result = Boolean.FALSE;
        if (input == null) {
            return result;
        }
        boolean is7Bit = isGSM_7Bit(input);
        if (!is7Bit) {
            // Kiem tra xem co phai la Ky Tu GSM 7 bit khong? 
            // Neu khong phai tra ve ket qua luon
//            MyLog.debug("Khong phai GMS 7 bit");
            return true;
        }
        char[] arrChar = input.toCharArray();
        for (char c : arrChar) {
            if (Character.UnicodeBlock.of(c) != Character.UnicodeBlock.BASIC_LATIN) {
                MyLog.logONDebug("Khong nam trong BASIC_LATIN:" + c + "=> Msg=" + input);
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isGSM_7Bit(String input) {
        boolean result;
        if (Tool.checkNull(input)) {
            return Boolean.FALSE;
        }
        Pattern pattern = Pattern.compile(GSM_CHARACTERS_REGEX);
        Matcher matcher = pattern.matcher(input);
        result = matcher.find();
        return result;
    }

    public static boolean isASCII(String input) {
        boolean isASCII = true;
        if (input == null) {
            return isASCII;
        }
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i);
            if (c > 0x7F) {
                isASCII = false;
                break;
            }
        }
        return isASCII;
    }
    private static final ArrayList<String> OTP_LIST = new ArrayList<String>();

    static {
        OTP_LIST.add("OTP");
        OTP_LIST.add("PIN");
        OTP_LIST.add("ma giao dich");
        OTP_LIST.add("ma xac thuc");
        OTP_LIST.add("ma xac nhan");
        OTP_LIST.add("chuoi chung thuc");
        OTP_LIST.add("chuoi xac nhan");
        OTP_LIST.add("chuoi xac thuc");
        OTP_LIST.add("chuoi kich hoat");
        OTP_LIST.add("mat khau");
        OTP_LIST.add("ma kich hoat");
        OTP_LIST.add("monitor");
        OTP_LIST.add("test");
        OTP_LIST.add("check");
        OTP_LIST.add("GD rut tien");
        OTP_LIST.add("Han muc con lai");
        OTP_LIST.add("vua chi tieu");
        OTP_LIST.add("VPBFC");
    }

    public static boolean isOTP(String msg) {
        boolean result = false;
        try {
            if (!Tool.checkNull(msg)) {
                msg = msg.toLowerCase();
                for (String one : OTP_LIST) {
                    if (msg.contains(one.toLowerCase())) {
                        result = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public static String phoneTo84(String number) {
        if (number == null) {
            number = "";
            return number;
        }
        number = number.replaceAll("o", "0");
        number = number.replaceAll("\\+", "");
        if (number.startsWith("84")) {
            return number;
        } else if (number.startsWith("0")) {
            number = "84" + number.substring(1);
        } else if (number.startsWith("+84")) {
            number = number.substring(1);
        } else {
            number = "84" + number;
        }
        return number;
    }

    public static boolean isASCII(char ch) {
        return ch < 128;
    }

    public static void SendAlert8x65(String message, String phone) {

        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("userName", "htcfe");
            params.put("passWord", "ohmygod39458");
            params.put("phone", phone);
            params.put("mess", message);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            Tool.out("PostData:" + postData);
            URL url = new URL("http://210.211.98.80:8765/service/notifybyPhone/MT" + "?" + postData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }
            String resultPartner = sb.toString();
            Tool.out("[===> ResultPartner:" + resultPartner);
            conn.disconnect();
        } catch (IOException e) {
            logger.error(Tool.getLogMessage(e));
        }
    }

    private static final int MAX_MULTIPART_MSG_SEGMENT_SIZE_UCS2 = 134;
    private static final int MAX_SINGLE_MSG_SEGMENT_SIZE_UCS2 = 70;
    private static final int MAX_MULTIPART_MSG_SEGMENT_SIZE_7BIT = 154;
    private static final int MAX_SINGLE_MSG_SEGMENT_SIZE_7BIT = 160;
    private static MessageClass messageClass = MessageClass.CLASS1;

    private static byte[][] splitUnicodeMessage(byte[] aMessage, Integer maximumMultipartMessageSegmentSize) {

        final byte UDHIE_HEADER_LENGTH = 0x05;
        final byte UDHIE_IDENTIFIER_SAR = 0x00;
        final byte UDHIE_SAR_LENGTH = 0x03;

        // determine how many messages have to be sent
        int numberOfSegments = aMessage.length / maximumMultipartMessageSegmentSize;
        int messageLength = aMessage.length;
        if (numberOfSegments > 255) {
            numberOfSegments = 255;
            messageLength = numberOfSegments * maximumMultipartMessageSegmentSize;
        }
        if ((messageLength % maximumMultipartMessageSegmentSize) > 0) {
            numberOfSegments++;
        }

        // prepare array for all of the msg segments
        byte[][] segments = new byte[numberOfSegments][];

        int lengthOfData;

        // generate new reference number
        byte[] referenceNumber = new byte[1];
        new Random().nextBytes(referenceNumber);

        // split the message adding required headers
        for (int i = 0; i < numberOfSegments; i++) {
            if (numberOfSegments - i == 1) {
                lengthOfData = messageLength - i * maximumMultipartMessageSegmentSize;
            } else {
                lengthOfData = maximumMultipartMessageSegmentSize;
            }

            // new array to store the header
            segments[i] = new byte[6 + lengthOfData];

            // UDH header
            // doesn't include itself, its header length
            segments[i][0] = UDHIE_HEADER_LENGTH;
            // SAR identifier
            segments[i][1] = UDHIE_IDENTIFIER_SAR;
            // SAR length
            segments[i][2] = UDHIE_SAR_LENGTH;
            // reference number (same for all messages)
            segments[i][3] = referenceNumber[0];
            // total number of segments
            segments[i][4] = (byte) numberOfSegments;
            // segment number
            segments[i][5] = (byte) (i + 1);

            // copy the data into the array
            System.arraycopy(aMessage, (i * maximumMultipartMessageSegmentSize), segments[i], 6, lengthOfData);

        }
        return segments;
    }

    public static int countLengthNew(String message) throws IOException {
        try {
            Alphabet alphabet = null;
            int maximumSingleMessageSize = 0;
            int maximumMultipartMessageSegmentSize = 0;
            byte[] byteSingleMessage = null;
            if (Gsm0338.isEncodeableInGsm0338(message)) {
                byteSingleMessage = message.getBytes();
                alphabet = Alphabet.ALPHA_DEFAULT;
                maximumSingleMessageSize = MAX_SINGLE_MSG_SEGMENT_SIZE_7BIT;
                maximumMultipartMessageSegmentSize = MAX_MULTIPART_MSG_SEGMENT_SIZE_7BIT;
            } else {
                byteSingleMessage = message.getBytes("UTF-16BE");
                alphabet = Alphabet.ALPHA_UCS2;
                maximumSingleMessageSize = MAX_SINGLE_MSG_SEGMENT_SIZE_UCS2;
                maximumMultipartMessageSegmentSize = MAX_MULTIPART_MSG_SEGMENT_SIZE_UCS2;
            }
            byte[][] byteMessagesArray = null;
            ESMClass esmClass = null;
            if (message.length() > maximumSingleMessageSize) {
                // split message according to the maximum length of a segment
                byteMessagesArray = splitUnicodeMessage(byteSingleMessage, maximumMultipartMessageSegmentSize);
                // set UDHI so PDU will decode the header
                esmClass = new ESMClass(MessageMode.DEFAULT, MessageType.DEFAULT, GSMSpecificFeature.UDHI);
            } else {
                byteMessagesArray = new byte[][]{byteSingleMessage};
                esmClass = new ESMClass();
            }

            System.out.println("Sending message " + message);
            System.out.printf("Message is %d characters long and will be sent as %d messages with params: %s %s ",
                    message.length(), byteMessagesArray.length, alphabet, messageClass);
            System.out.println();
            if (byteMessagesArray != null) {
                return byteMessagesArray.length;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }

    }

    static int countLengthGSM(String input) {
//        long s = System.currentTimeMillis();
        int length = 0;
        for (int pos = 0; pos < input.length(); pos++) {
            char ch = input.charAt(pos);
            switch (ch) {
                case '|':
                case '^':
                case '€':
                case '{':
                case '}':
//                case '\n':
                case '[':
                case ']':
                case '~':
//                case '\\':
                    length += 2;
                    break;
                default:
                    length += 1;
            }
        }
//        long e = System.currentTimeMillis() - s;
//        MyLog.debug("Proces countLengthGSM:" + e);
        return length;
    }

    public static int countSmsQC(String mess, String oper) {
        int count;
        if (!Tool.checkNull(mess)) {
            int length = countLengthGSM(mess);
            if (oper.equals(OPER.VINA.val)) {
                if (length <= 122) {
                    count = 1;
                } else if (length > 122 && length <= 268) {
                    count = 2;
                } else if (length > 268 && length <= 421) {
                    count = 3;
                } else {
                    count = REJECT_MSG_LENG;
                }
            } else {
                if (length <= 160) {
                    count = 1;
                } else if (length > 160 && length <= 306) {
                    count = 2;
                } else if (length > 306 && length <= 459) {
                    count = 3;
                } else {
                    count = REJECT_MSG_LENG;
                }
            }
        } else {
            count = 0;
        }
        return count;
    }

    public static int countMinQC(String mess) {
        int count;
        if (!Tool.checkNull(mess)) {
            int length = countLengthGSM(mess);
            if (length <= 122) {
                count = 1;
            } else if (length > 122 && length <= 268) {
                count = 2;
            } else if (length > 268 && length <= 421) {
                count = 3;
            } else {
                count = REJECT_MSG_LENG;
            }
        } else {
            count = 0;
        }
        return count;
    }

    public static int countFast(String mess) {
        int tmpLeng = countLengthGSM(mess);
        int result = tmpLeng / 157;
        if (tmpLeng % 157 != 0) {
            result += 1;
        }
        return result;
    }

    public static int countFastUnicode(String mess) {
        int tmpLeng = mess.length();
        int result = tmpLeng / 67;
        if (tmpLeng % 67 != 0) {
            result += 1;
        }
        return result;
    }

    public static int countSmsCSKH(String mess, String oper) {
        int count;
        if (!Tool.checkNull(mess)) {
            int length = countLengthGSM(mess);
            MyLog.logDebug("countSmsCSKH countLengthGSM:" + length);
            if (oper.equals(OPER.VIETTEL.val)) {
                if (length <= 160) {
                    count = 1;
                } else if (length > 160 && length <= 306) {
                    count = 2;
                } else if (length > 306 && length <= 459) {
                    count = 3;
//                } else if (length > 459 && length <= 612) {
//                    count = 4;
                } // VIETTEL BO TIN NHAN THU 4 DI
                else {
                    count = REJECT_MSG_LENG;
                }
            } else if (oper.equals(OPER.VINA.val)) {
                if (length <= 160) {
                    count = 1;
                } else if (length > 160 && length <= 306) {
                    count = 2;
                } else if (length > 306 && length <= 459) {
                    count = 3;
                } else if (length > 459 && length <= 612) {
                    count = 4;
                } else {
                    count = REJECT_MSG_LENG;
                }
            } else if (oper.equals(OPER.MOBI.val)) {
                if (length <= 160) {
                    count = 1;
                } else if (length > 160 && length <= 306) {
                    count = 2;
                } else if (length > 306 && length <= 459) {
                    count = 3;
                } else if (length > 459 && length <= 612) {
                    count = 4;
                } else {
                    count = REJECT_MSG_LENG;
                }
            } else if (length <= 160) {
                count = 1;
            } else if (length > 160 && length <= 306) {
                count = 2;
            } else if (length > 306 && length <= 459) {
                count = 3;
            } else if (length > 459 && length <= 612) {
                count = 4;
            } else {
                count = REJECT_MSG_LENG;
            }
        } else {
            count = 0;
        }
        return count;
    }

    public static int countSmsBrandCSKHUnicode(String mess) {
        int count;
        if (!Tool.checkNull(mess)) {

            int length = countLengthGSM(mess);
//            MyLog.debug("======:::::==============:::" + length);
            if (length <= 70) {
                count = 1;
            } else if (length > 70 && length <= 134) {
                count = 2;
            } else if (length > 134 && length <= 201) {
                count = 3;
            } else if (length > 201 && length <= 268) {
                count = 4; //southtelecom
//            } else if (length > 268 && length <= 335) {
//                count = 5; // VMG
            } else {
                count = 0;
            }
        } else {
            count = 0;
        }
        return count;
    }

    public static boolean validTemplate(String input, String patten) {
        boolean result = false;
        try {
            Pattern p = Pattern.compile(patten);
            Matcher m = p.matcher(input);
            result = m.matches();
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public static boolean validTemplate(String input) {
        boolean result = false;
        try {
            Pattern p = Pattern.compile(".{1,}", Pattern.DOTALL);
            Matcher m = p.matcher(input);
            result = m.matches();
            Tool.out("result:" + result);
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public static boolean validTemplate(String input, String[] patten) {
        boolean result = false;
        try {
            if (patten != null && patten.length > 0) {
                for (String onePatten : patten) {
//                    Tool.out("onePatten:" + onePatten);
                    Pattern p = Pattern.compile(onePatten, Pattern.DOTALL);
                    Matcher m = p.matcher(input);
                    result = m.matches();
//                    Tool.out("result:" + result);
                    if (result) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    //VIETTEL 016x -> 03x
    //MOBI 0120 -> 070,0121 ->079, 0122->077,0126->076,0128->078
    //VINA 0123 -> 083,0124 ->084, 0125->085,0127->081,0129->082
    //VNM 18x -> 0186 ->056, 0188->058
    public static boolean validPhoneVN(String phone) {
        phone = phoneTo84(phone);
        String regex = ""
                + "^"
                + "((843|845|847|848|849)\\d{8})|(841\\d{9})"
                + "$";
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);
        // Now create matcher object.
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static enum OPER {

        VIETTEL("VTE"),
        VINA("GPC"),
        MOBI("VMS"),
        VNM("VNM"),
        BEELINE("BL");
        public String val;

        public String getVal() {
            return val;
        }

        private OPER(String val) {
            this.val = val;
        }
    }

    /**
     * PLA TUAN Xap Xep COmmandCode Theo Do Dai giam dan
     *
     * @param allCommandCode
     * @return
     */
    public static String[] arrangeCommandCode(String[] allCommandCode) {
        try {
            for (int i = 0; i < allCommandCode.length; i++) {
                for (int j = i + 1; j < allCommandCode.length; j++) {
                    String stem;
                    if (allCommandCode[j].length() > allCommandCode[i].length()) {
                        stem = allCommandCode[i].toUpperCase();
                        allCommandCode[i] = allCommandCode[j].toUpperCase();
                        allCommandCode[j] = stem;
                    }
                }
            }
        } catch (Exception e) {
            return allCommandCode;
        }
        return allCommandCode;
    }

    /**
     * PLA TUAN Loai Bo Cac Ky Tu Dac biet trong Msg
     *
     * @param nick
     * @return
     */
    public static String sumNick(String nick) {
        if (nick == null || "".equals(nick)) {
            return null;
        }
        nick = nick.trim();
        int sum = 0;
        if (nick.length() < 2 && isNumberic(nick)) {
            return nick;
        }
        nick = nick.toUpperCase();
        for (int i = 0; i < nick.length(); i++) {
            char ch = nick.charAt(i);
            if (ch == 'A' || ch == 'J' || ch == 'S') {
                sum++;
                continue;
            }
            if (ch == 'B' || ch == 'K' || ch == 'T') {
                sum += 2;
                continue;
            }
            if (ch == 'C' || ch == 'L' || ch == 'U') {
                sum += 3;
                continue;
            }
            if (ch == 'D' || ch == 'M' || ch == 'V') {
                sum += 4;
                continue;
            }
            if (ch == 'E' || ch == 'N' || ch == 'W') {
                sum += 5;
                continue;
            }
            if (ch == 'F' || ch == 'O' || ch == 'X') {
                sum += 6;
                continue;
            }
            if (ch == 'G' || ch == 'P' || ch == 'Y') {
                sum += 7;
                continue;
            }
            if (ch == 'H' || ch == 'Q' || ch == 'Z') {
                sum += 8;
                continue;
            }
            if (ch == 'I' || ch == 'R') {
                sum += 9;
            }
        }

        String sTmp = (new StringBuilder()).append("").append(sum).toString();
        sum = 0;
        int iTmp;
        for (; sTmp.length() != 1; sTmp = String.valueOf(iTmp)) {
            iTmp = 0;
            for (int i = 0; i < sTmp.length(); i++) {
                char temp = sTmp.charAt(i);
                if (Character.isDigit(temp)) {
                    iTmp += Integer.parseInt(String.valueOf(temp));
                }
            }

        }
        return sTmp;
    }

    //VIETTEL 016x -> 03x
    //MOBI 0120 -> 070,0121 ->079, 0122->077,0126->076,0128->078
    //VINA 0127->081,0129->082,0123 -> 083,0124 ->084, 0125->085
    //VNM 18x -> 0186 ->056, 0188->058
    public static String buildMobileOperator(String phone) {
        String mobileOperator = "OTHER";
        phone = phoneTo84(phone);                   // Chuyen ve 1 dinh dang cho chuan
//        System.out.println("Affter to 84:" + phone);
        boolean isPhoneVn = validPhoneVN(phone);    // Fix loi So dien thoai dai hon 10 hoac 11 so
        if (!isPhoneVn) {
            return mobileOperator;
        }
//        System.out.println("is phone Valid");
        if (//-
                phone.startsWith("8491")
                || phone.startsWith("8494")
                || phone.startsWith("84123") 
                || phone.startsWith("84124") 
                || phone.startsWith("84125") 
                || phone.startsWith("84127") 
                || phone.startsWith("84129")
                || phone.startsWith("8481") 
                || phone.startsWith("8482") 
                || phone.startsWith("8483") 
                || phone.startsWith("8484") 
                || phone.startsWith("8485") 
                || phone.startsWith("8487") 
                || phone.startsWith("8488")// NEW
                ) {
            //VINA
            mobileOperator = OPER.VINA.val;
        } else if (//--
                phone.startsWith("8490")
                || phone.startsWith("8493")
                || phone.startsWith("84120") 
                || phone.startsWith("84121") 
                || phone.startsWith("84122") 
                || phone.startsWith("84126") 
                || phone.startsWith("84128")
                || phone.startsWith("8489")
                || phone.startsWith("8470") 
                || phone.startsWith("8476") 
                || phone.startsWith("8477") 
                || phone.startsWith("8478") 
                || phone.startsWith("8479") // NEW
                ) {
            // MOBILE
            mobileOperator = OPER.MOBI.val;
        } else if (//--
                phone.startsWith("8498")
                || phone.startsWith("8497")
                || phone.startsWith("8496")
                || phone.startsWith("8416")
                || phone.startsWith("8486")
                || phone.startsWith("843") // Chuyen Mang Moi 016 -> 03x
                ) {
            mobileOperator = OPER.VIETTEL.val;
        } else if (phone.startsWith("8492")
                || phone.startsWith("84188")
                // || phone.startsWith("84187") 
                || phone.startsWith("84186")
                // || phone.startsWith("84184") 
                || phone.startsWith("8452")
                || phone.startsWith("8456") // 0186 -> 056
                || phone.startsWith("8458") // 0188 -> 058
                ) {
            // VIET NAM MOBILE
            mobileOperator = OPER.VNM.val;
        } else if (phone.startsWith("8499")
                || phone.startsWith("84199")
                || phone.startsWith("8459")) {
            mobileOperator = OPER.BEELINE.val;
        } else {
            mobileOperator = "OTHER";
        }
        return mobileOperator;
    }
    
    public static String convert11To10(String phoneString){
        String result = phoneString;
        String beginPart = phoneString.substring(0,5);
//        System.out.println("phan dau="+beginPart);
        switch (beginPart) {
            case "84162":  result = "8432" + phoneString.substring(5);
                     break;
            case "84163":  result = "8433" + phoneString.substring(5);
                     break;
            case "84164":  result = "8434" + phoneString.substring(5);
                     break;
            case "84165":  result = "8435" + phoneString.substring(5);
                     break;
            case "84166":  result = "8436" + phoneString.substring(5);
                     break;
            case "84167":  result = "8437" + phoneString.substring(5);
                     break;
            case "84168":  result = "8438" + phoneString.substring(5);
                     break;
            case "84169":  result = "8439" + phoneString.substring(5);
                     break;
            case "84120":  result = "8470" + phoneString.substring(5);
                     break;
            case "84121": result = "8479" + phoneString.substring(5);
                     break;
            case "84122": result = "8477" + phoneString.substring(5);
                     break;
            case "84126": result = "8476" + phoneString.substring(5);
                     break;
            case "84128": result = "8478" + phoneString.substring(5);
                     break;
            case "84123": result = "8483" + phoneString.substring(5);
                     break;
            case "84124": result = "8484" + phoneString.substring(5);
                     break;
            case "84125": result = "8485" + phoneString.substring(5);
                     break;
            case "84127": result = "8481" + phoneString.substring(5);
                     break;
            case "84129": result = "8482" + phoneString.substring(5);
                     break;
            case "84188": result = "8458" + phoneString.substring(5);
                     break;
            case "84199": result = "8459" + phoneString.substring(5);
                     break;
            case "84186": result = "8456" + phoneString.substring(5);
                     break;
            default: break;
        }
        return result;
    }

    public static String getTimeString() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "HH:mm:ss";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(cal.getTime());
    }

    public static byte[] hexToByte(String s) {
        int l = s.length() / 2;
        byte data[] = new byte[l];
        int j = 0;
        for (int i = 0; i < l; i++) {
            char c = s.charAt(j++);
            int n, b;
            n = HEXINDEX.indexOf(c);
            b = (n & 0xf) << 4;
            c = s.charAt(j++);
            n = HEXINDEX.indexOf(c);
            b += (n & 0xf);
            data[i] = (byte) b;
        }
        return data;
    }

    public static String stringToHexString(String str) {
        byte[] bytes;
        String temp = "";
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (Exception ex) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            temp = temp + Integer.toHexString(bytes[i]);
        }
        return temp;
    }

    public static String stringToHex(String str) {
        char[] chars = str.toCharArray();
        StringBuilder strBuffer = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            strBuffer.append(Integer.toHexString((int) chars[i]));
        }
        return strBuffer.toString();
    }

    /**
     * Kiểm tra xem 1 String có phải là số
     *
     * @param sNumber
     * @return
     */
    public static boolean isNumberic(String sNumber) {
        if (sNumber == null || "".equals(sNumber)) {
            return false;
        }
        for (int i = 0; i < sNumber.length(); i++) {
            char ch = sNumber.charAt(i);
            char ch_max = '9';
            char ch_min = '0';
            if (ch < ch_min || ch > ch_max) {
                return false;
            }
        }

        return true;
    }

    public static String[] splitString(String s, int width) {
        try {
            if (width == 0) {
                String[] ret = new String[1];
                ret[0] = s;
                return ret;
            } else if (s.isEmpty()) {
                return new String[0];
            } else if (s.length() <= width) {
                String[] ret = new String[1];
                ret[0] = s;
                return ret;
            } else {
                int NumSeg = s.length() / width + 1;
                String[] ret = new String[NumSeg];
                int startPos = 0;

                for (int i = 0; i < NumSeg - 1; i++) {
                    ret[i] = s.substring(startPos, ((width * (i + 1))));
                    startPos = (i + 1) * width;
                }
                ret[NumSeg - 1] = s.substring(startPos, s.length());
                return ret;
            }
        } catch (Exception e) {
            return new String[0];
        }
    }

    
}

class Gsm0338 {

    private static final short ESC_CHARACTER = (short) 27;

    private static final short[] isoGsm0338Array = {64, 163, 36, 165, 232, 233, 249, 236, 242, 199, 10, 216, 248, 13,
        197, 229, 0, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 198, 230, 223, 201, 32, 33, 34, 35, 164, 37, 38, 39, 40, 41,
        42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 161, 65, 66, 67,
        68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 196, 214, 209,
        220, 167, 191, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115,
        116, 117, 118, 119, 120, 121, 122, 228, 246, 241, 252, 224};

    private static final short[][] extendedIsoGsm0338Array = {{10, 12}, {20, 94}, {40, 123}, {41, 125},
    {47, 92}, {60, 91}, {61, 126}, {62, 93}, {64, 124}, {101, 164}};

    public static boolean isEncodeableInGsm0338(String isoString) {
        byte[] isoBytes = isoString.getBytes();
        outer:
        for (int i = 0; i < isoBytes.length; i++) {
            for (int j = 0; j < isoGsm0338Array.length; j++) {
                if (isoGsm0338Array[j] == isoBytes[i]) {
                    continue outer;
                }
            }
            for (int j = 0; j < extendedIsoGsm0338Array.length; j++) {
                if (extendedIsoGsm0338Array[j][1] == isoBytes[i]) {
                    continue outer;
                }
            }
            return false;
        }
        return true;
    }
}
