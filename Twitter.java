class Twitter {

    /** Initialize your data structure here. */
    
    int timestamp, feedsize;
    
    Map<Integer, List<Tweet>> usertweetmap;
    Map<Integer, Set<Integer>> userfollowsmap;
    
    public Twitter() {
        timestamp = 0;
        feedsize = 10;
        usertweetmap = new HashMap<>();
        userfollowsmap = new HashMap<>();
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        isFirstTime(userId);
        
        List<Tweet> tweets = uesrtweetmap.get(userId);
        tweets.add(new Tweet(tweetId, timestamp++));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        isFirstTime(userId);
        
        List<Tweet> feeds = new ArrayList<>();
        
        Set<Integer> followees = userfollowsmap.get(userId);
        
        for(Integer followee: followees){
            feeds.addAll(usertweetmap.get(followee));
        }
        
        Collections.sort(feeds, (a,b) -> (b.createdAt - a.createdAt));
        
        Iterator<Tweet> iterator = feeds.iterator();
        
        List<Integer> recentfeed = new ArrayList<>();
        int count = feedsize;
        
        while(count > 0 && iterator.hasnext()){
            recentfeed.add(iterator.next.tweetId);
            count--;
        }
        return recentfeed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        isFirstTime(followerId);
        isFirstTime(followeeId);
        
        Set<Integer> followees = userfollowsmap.get(followerId); 
        followees.add(followeeId);
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        isFirstTime(followerId);
        isFirstTime(followeeId);
        
        Set<Integer> followees = userfollowsmap.get(followerId); 
        
        if(followerId != followeeId)
        followees.remove(followeeId);
        
    }
    
    private void isFirstTime(int userID){
        Set<Integer> followees = userfollowsmap.get(userID);
        if(followees == null){
            followees = new HashSet<>();
            followees.add(userID);
            userfollowsmap.put(userID, followees );
        }
        List<Tweet> tweets = usertweetmap.get(userID);
        if(tweets == null){
            tweets = new LinkedList<>();
            usertweetmap.put(userID, tweets);
        }
    }
    
    
}

class Tweet {
        int tweetId;
        int createdAt;
        
         Tweet(int tweetId, int createdAt){
             this.tweetId = tweetId;
             this.createdAt = createdAt;
         }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
