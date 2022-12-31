package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
//@SpringBootTest
class SankunNewApplicationTests {

	@Test
	public void test() throws Exception {
		String bizRegNoCheck = makeAvailableBizRegNo("106814101");
		String bizRegNoCheckParse = bizRegNoCheck.substring(0,3) + "-"+bizRegNoCheck.substring(3,5) + "-"+bizRegNoCheck.substring(5,10);
		System.out.println(bizRegNoCheckParse);
	}
//106-81-41079
	public String pad(String src, String pad, int totLen, int mode){
		  String paddedString = "";

		  if(src == null) return "";
		  int srcLen = src.length();

		  if((totLen<1)||(srcLen>=totLen)) return src;

		  for(int i=0; i< (totLen-srcLen); i++){
		   paddedString += pad;
		  }

		  if(mode == -1)
		   paddedString += src; // front padding
		  else
		       paddedString = src + paddedString; //back padding

		  return paddedString;
		 }
	
	private String makeAvailableBizRegNo(String bizRegNoFront) {
		int chkNum = 0;
		String brnoFrontNumStr = bizRegNoFront.replaceAll("[^0-9]", "");
		List<Integer> brnoFrontNum = new ArrayList<Integer>();
		for (int i = 0; i < brnoFrontNumStr.length(); i++) {
			brnoFrontNum.add(Integer.valueOf(Integer.parseInt(String.valueOf(brnoFrontNumStr.charAt(i)))));
		}
		List<Integer> chkRegNum = new ArrayList<Integer>(Arrays.asList(1, 3, 7, 1, 3, 7, 1, 3, 5));

		for (int i = 0; i <= 8; i++) {
			chkNum += (brnoFrontNum.get(i) * chkRegNum.get(i));
		}
		chkNum += (int) ((brnoFrontNum.get(8) * chkRegNum.get(8)) / 10);
		chkNum = chkNum % 10;
		chkNum = 10 - chkNum;
		chkNum = chkNum % 10;

		String result = bizRegNoFront.concat(Integer.toString(chkNum));

		return result;
	}
}
