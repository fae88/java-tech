package http.test;

class Solution {
  public static void main(String[] args) {

      for (int i = 0; i <= 10000; i ++ ) {
		if (!containsFour(i)) {
        	System.out.println(i);
        }
      }
  }
  
  /**
  * 数字中是否包含4，返回true为包含
  **/
  public static boolean containsFour(int target) {
  
  	int x = 0;
  
  	while ( target > 0) {
    	x = target % 10;
        if (x == 4) {
        	return true;
        }
      	target /= 10;
    }
    return false;		
  }
  
}