package com.delozoya;

import com.delozoya.BotConfig;
import com.delozoya.Commands;
import com.delozoya.ResourcesStrings;
import com.delozoya.services.*;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.methods.SendPhoto;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.delozoya.updateshandlers.EchoHandlers;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.sql.SQLException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Main class to create all bots
 * @date 20 of June of 2015
 */
/**
 * <p>This is a code example of Twitter4J async API.<br>
 * Usage: java twitter4j.examples.AsyncUpdate <i>davidlozoo</i> <i>PIPITA20</i> <i>text</i><br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class Main {
    private static final String LOGTAG = "MAIN";
    private static final Object LOCK = new Object();
    public static String[]us ={"@jacvega","@potichero"};

    public static void main(String[] args) throws TwitterException, InterruptedException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("YGK1PRnbYeadk3fydH1Tmw6Ez")
                .setOAuthConsumerSecret("aYhj8hN9lAozo7ze8DDiPk1RB0coUTOPeBj8MR9lCf0ZrBYutx")
                .setOAuthAccessToken("698088269335158784-G9FIb7yaA43g48Oh7woML23uFvO6lB7")
                .setOAuthAccessTokenSecret("iID85PkbzgXbF7Ysj3BBAJZoCm7EJd3xqw46A3VPvqFbP");
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                //if(status.getUser().getScreenName().equals("davidlozoo")) {
                MediaEntity[] media = status.getMediaEntities(); //get the media entities from the status
                for(MediaEntity m : media){ //search trough your entities
                    System.out.println(m.getMediaURL()); //get your url!
                }
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                if(status.getUser().getScreenName().equals("NF1bot")||status.getUser().getScreenName().equals("a3formula1")||status.getUser().getScreenName().equals("cardriverthef1")||
                        status.getUser().getScreenName().equals("F1")||status.getUser().getScreenName().equals("F1_LAT")) {
                    EchoHandlers.sendMenssage1("@" + status.getUser().getScreenName() + " - " + status.getText());


                }


                // }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.user(us);

        try {
            telegramBotsApi.registerBot(new EchoHandlers());
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

}
