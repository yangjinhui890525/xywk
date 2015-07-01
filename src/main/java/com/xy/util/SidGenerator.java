package com.xy.util;


/**
 * 
 * Copyright (C) 2006 Bejing iActive Tec Co.,Ltd File: SidGenerator.java
 * 
 * file description
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE FOUNDATION OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * @author Lu Guo Xiang 2006-7-31
 */
public class SidGenerator {

	
	private static char[] SID_CH=new char[26+10];
	static{
		for(char c='A';c<='Z';c++)
			SID_CH[c-'A']=c;
		for(char c='0';c<='9';c++)
			SID_CH[c-'0'+26]=c;		
	}
	public static final int SID_LEN=18;
	/**
	 * 随机产生一个包括大写字母和数字的长度为18的字符串，以prefix开头
	 * @param preffix:
	 * 			字符串前缀
	 */
	public static String genSid(char preffix) {
		StringBuffer buf=new StringBuffer();
		buf.append(preffix);
		for(int i=1;i<SID_LEN;i++){
			int index=(int)(Math.random()*SID_CH.length);
			buf.append(SID_CH[index]);
		}
		return buf.toString();
	}
	
	
	public static String genVerifyCode(int len) {
		StringBuffer buf=new StringBuffer();
		for(int i=0;i<len;i++){
			int index=(int)(Math.random()*SID_CH.length);
			buf.append(SID_CH[index]);
		}

		return buf.toString();
	}	
	
	public static void main(String[] args) {
		String sid = genVerifyCode(8);
		System.out.println(sid);
	}

}
