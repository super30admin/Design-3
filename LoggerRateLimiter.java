// Time Complexity :  O(N)
// Space Complexity : O(N), N = #unique messages
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class LoggerRateLimiter {
    class Logger {
        HashMap<String, Integer> map;
        public Logger() {
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if(map.containsKey(message)) {
                int endTime =  map.get(message);
                if(timestamp <endTime)  {
                    return false;
                }
            }
            map.put(message, timestamp + 10);
            return true;
        }
    }

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
}
