class Solution {
 private int toSeconds(String time) {
 int h = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
 int m = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
 int s = (time.charAt(6) - '0') * 10 + (time.charAt(7) - '0');
 return h * 3600 + m * 60 + s;
 }
 public int secondsBetweenTimes(String startTime, String endTime) {
  return toSeconds(endTime) - toSeconds(startTime);
    }
}