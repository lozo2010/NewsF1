package com.delozoya.services;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by David on 12/02/2016.
 */
public class TwitterConfig {


    public TwitterConfig(){

    }

    public static Twitter getTwitter(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("YGK1PRnbYeadk3fydH1Tmw6Ez")
                .setOAuthConsumerSecret("aYhj8hN9lAozo7ze8DDiPk1RB0coUTOPeBj8MR9lCf0ZrBYutx")
                .setOAuthAccessToken("698088269335158784-G9FIb7yaA43g48Oh7woML23uFvO6lB7")
                .setOAuthAccessTokenSecret("iID85PkbzgXbF7Ysj3BBAJZoCm7EJd3xqw46A3VPvqFbP");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }
}
