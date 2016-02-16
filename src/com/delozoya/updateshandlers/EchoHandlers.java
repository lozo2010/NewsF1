package com.delozoya.updateshandlers;

import com.delozoya.BotConfig;
import com.delozoya.Commands;
import com.delozoya.ResourcesStrings;
import com.delozoya.services.*;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.methods.SendPhoto;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by David on 03/02/2016.
 */
public class EchoHandlers extends TelegramLongPollingBot {
    private static final String LOGTAG = "ECHOHANDLERS";

    private static final Integer CACHETIME = 1000;

    private static final String helpMessage = "Usa este bot para recibir informacion de F1. Usa palabras clave como:\n" +
            "circuitos\n" +
            "pilotos\n" +
            "escuderias\n" +
            "GP\n" +
            "Ejemplo: @NewsF1 GP";

    private final ConcurrentLinkedQueue<Integer> languageMessages = new ConcurrentLinkedQueue<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        System.out.println(update.getMessage().getText());

        if (message != null && message.hasText()) {
            try {
                System.out.println(message.getFrom().getId());
               /* SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(message.getText());
                sendMessage(sendMessageRequest);*/
                handleEcho(update);
            } catch (InvalidObjectException e) {
                BotLogger.error(LOGTAG, e);

            }
        }

        if (update.hasInlineQuery()) {

            String query = update.getInlineQuery().getQuery();
            if (query.isEmpty()){
                AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
                answerInlineQuery.setInlineQueryId(update.getInlineQuery().getId());
                answerInlineQuery.setCacheTime(CACHETIME);
                answerInlineQuery.setResults(new ArrayList<>());
                try {
                    sendAnswerInlineQuery(answerInlineQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (query.equals("circuitos")){
                AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
                answerInlineQuery.setInlineQueryId(update.getInlineQuery().getId());
                answerInlineQuery.setCacheTime(CACHETIME);
                answerInlineQuery.setResults(createcircuitos());


                try {
                    sendAnswerInlineQuery(answerInlineQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (query.equals("GP")){
                System.out.println(query);
            }
        }

        if (message != null && !message.hasText() && !update.hasInlineQuery()) {
            try {
                photoId(update);
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Create a help message when an user try to send messages directly to the bot
     * @param message Received message
     * @return SendMessage method
     */
    private static SendMessage getHelpMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(helpMessage);
        return sendMessage;
    }

    private static List<InlineQueryResult> createcircuitos() {
        List<InlineQueryResult> results = new ArrayList<>();

        InlineQueryResultArticle australia = new InlineQueryResultArticle();
        australia.setDisableWebPagePreview(true);
        australia.enableMarkdown(true);
        australia.setId("0");
        australia.setMessageText(Circuito.Australia());
        australia.setTitle("Australia");
        australia.setDescription("Circuito Albert Park, Melburne");

        InlineQueryResultArticle barhein = new InlineQueryResultArticle();
        barhein.setDisableWebPagePreview(true);
        barhein.enableMarkdown(true);
        barhein.setId("1");
        barhein.setMessageText(Circuito.Barein());
        barhein.setTitle("Barhein");
        barhein.setDescription("Circuito Sakir, Barhein");

        results.add(australia);
        results.add(barhein);

        return results;
    }

    @Override
    public String getBotToken() {
        return BotConfig.TOKENF1NEWS;
    }


    @Override
    public String getBotUsername() {
        return BotConfig.USERNAMF1NEWS;
    }

    private void handleEcho(Update update) throws InvalidObjectException {
        Message message = update.getMessage();
        int userId = message.getFrom().getId();


        if (message.getText().startsWith(Commands.startCommand)) {
            System.out.println(message.getText());
            onStartdirectionsCommand(message);
        }else if (message.getText().startsWith(Commands.circuitosCommand)||message.getText().startsWith(Emoji.CIRCUITO.toString())) {
            System.out.println(message.getText());
            createKeyCircuitos(message);
        }else if (message.getText().startsWith(Commands.calendarioCommand)||message.getText().startsWith(Emoji.CALENDAR.toString())) {
            SendPhoto calendario= new SendPhoto();
            calendario.setPhoto(ResourcesStrings.id_calendario);
            calendario.setChatId(message.getChatId() + "");
            try {
                sendPhoto(calendario);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (message.getText().startsWith(Commands.nextGpCommand)||message.getText().startsWith(Emoji.NEXTGP.toString())) {
            System.out.println(message.getText());
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());

            sendMessageRequest.setText(InfoGP.nextGPinfo());
            try {
                sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
        }else if (message.getText().startsWith(Commands.infoActualGPCommand)||message.getText().startsWith(Emoji.ACTUAL.toString())) {
            System.out.println(message.getText());
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());
            SendPhoto horario = new SendPhoto();
            horario.setNewPhoto("/home/server/Escritorio/BotF1/Australia.png","Australia.png");
            horario.setChatId(message.getChatId() + "");
            sendMessageRequest.setText(InfoGP.getInfo());
            try {
                sendMessage(sendMessageRequest);
                sendPhoto(horario);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }

        }else if (message.getText().startsWith(Commands.clasificacionCommand)||message.getText().startsWith(Emoji.CLASI.toString())) {
            System.out.println(message.getText());
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());
            sendMessageRequest.setText("http://www.caranddriverthef1.com/formula1/mundial/clasificacion/pilotos-equipos/2016");
            try {
                sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (message.getText().startsWith(Commands.pretemporadaCommand)) {

        }else if (message.getText().startsWith(Commands.abandonosCommand)) {

        }else if (message.getText().startsWith(Commands.pilotosCommand)||message.getText().startsWith(Emoji.PILOTO.toString())) {
            System.out.println(message.getText());
            createKeyPilotos(message);
        }else if(message.getText().startsWith(Commands.startservice)||message.getText().startsWith(Emoji.NEWSPAPER.toString())) {
            System.out.println(message.getText());
            try {
                Noticias.addID(String.valueOf(message.getFrom().getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (message.getText().startsWith(Commands.help)||message.getText().startsWith(Emoji.HELP.toString())){
            System.out.println(message.getText());
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());
            sendMessageRequest.setText("/start - Start Bot\n" +
                    "/noticias - Suscribirse a noticias y avisos de la F1\n" +
                    "/offnoticias - Darse de baja del servicio de noticias\n" +
                    "/circuitos - Informacion sobre los circuitos de esta temporada\n" +
                    "/calendario - Calendario Formula1 2016\n" +
                    "/nextgp - Siguiente Gran Premio\n" +
                    "/infogp - Informacion sobre el Gran Premio Actual\n" +
                    "/clasificacion - Clasificacion de la temporada Actual\n" +
                    "/pilotos - Informacion sobre los pilotos\n" +
                    "/escuderias - Informacion sobre los Equipos\n" +
                    "/help - Ayuda");
            try {
                sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (message.getText().startsWith(Commands.stopservice)||message.getText().startsWith(Emoji.STOPNOT.toString())){
            System.out.println(message.getText());
            try {
                Noticias.deleteID(String.valueOf(message.getFrom().getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (message.getText().startsWith(Commands.escuderiasCommand)||message.getText().startsWith(Emoji.ESCUDERIA.toString())) {
            System.out.println(message.getText());
            createKeyEscuderia(message);
        }else if (message.getText().equals("Circuito")){
            circuitoRecibido(message);
        }else if (message.getText().equals("Cancelar")){
            Inicio(message);
            //sendHideKeyboard(0,message.getChatId(),message.getMessageId());
        }else if (message.getText().substring(0,6).equals("Dorsal")){
            System.out.println(message.getText().substring(0,message.getText().indexOf(" -")));
            setPiloto(message);
        }else if (message.getText().substring(0,9).equals("Escuderia")){
            System.out.println(message.getText().substring(message.getText().indexOf("- ")));
            setEscuderia(message);

        }else if (message.getText().substring(0,2).equals("GP")){
            System.out.println(message.getText().substring(0,message.getText().indexOf("-")));
            setGP(message);

        }
    }

    private void photoId(Update update) throws InvalidObjectException {
        System.out.println(update.getMessage().getPhoto().get(0).getFileId());
    }

    private void sendHideKeyboard(Integer userId, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplayToMessageId(messageId);
        sendMessage.setText("Salir");

        ReplyKeyboardHide replyKeyboardHide = new ReplyKeyboardHide();
        replyKeyboardHide.setSelective(true);
        replyKeyboardHide.setHideKeyboard(true);
        sendMessage.setReplayMarkup(replyKeyboardHide);

        try {
            sendMessage(sendMessage);
          //  DatabaseManager.getInstance().insertWeatherState(userId, chatId, STARTSTATE);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }

    }

    public ReplyKeyboardMarkup keyStart(Message message){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<List<String>> keyboard = new ArrayList<>();
        List<String> keyboardFirstRow = new ArrayList<>();
        keyboardFirstRow.add(Emoji.NEWSPAPER+" "+"Suscribirse Noticias");
        keyboardFirstRow.add(Emoji.STOPNOT+" Baja de Noticias");
        List<String> keyboard2Row = new ArrayList<>();
        keyboard2Row.add(Emoji.CIRCUITO+" Circuitos");
        keyboard2Row.add(Emoji.CALENDAR+" Calendario");
        List<String> keyboard3Row = new ArrayList<>();
        keyboard3Row.add(Emoji.NEXTGP+" Siguente GP");
        keyboard3Row.add(Emoji.ACTUAL+" GP Actual");
        List<String> keyboard4Row = new ArrayList<>();
        keyboard4Row.add(Emoji.CLASI+" Clasificacion");
        keyboard4Row.add(Emoji.PILOTO+" Pilotos");
        List<String> keyboard5Row = new ArrayList<>();
        keyboard5Row.add(Emoji.ESCUDERIA+" Escuderia");
        keyboard5Row.add(Emoji.HELP+" Help");
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboard2Row);
        keyboard.add(keyboard3Row);
        keyboard.add(keyboard4Row);
        keyboard.add(keyboard5Row);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(false);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        return replyKeyboardMarkup;
    }

    private void Inicio(Message message){
        try {
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setReplayToMessageId(message.getMessageId());
            sendMessageRequest.setChatId(message.getChatId().toString());
            sendMessageRequest.enableMarkdown(true);



            sendMessageRequest.setReplayMarkup(keyStart(message));
            sendMessageRequest.setText("¿Que información necesita?");
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void onStartdirectionsCommand(Message message) {
        try {
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setReplayToMessageId(message.getMessageId());
            sendMessageRequest.setChatId(message.getChatId().toString());
            sendMessageRequest.enableMarkdown(true);



            sendMessageRequest.setReplayMarkup(keyStart(message));
            sendMessageRequest.setText(ResourcesStrings.start);
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void circuitoRecibido(Message message)throws InvalidObjectException {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId().toString());
        //Creamos circuitos

        SendPhoto p1 = new SendPhoto();
        p1.setPhoto(Circuito.id_barcelona);
        p1.setChatId(message.getChatId() + "");

        Circuito cir = new Circuito("Barcelona");

        sendMessageRequest.setReplayToMessageId(message.getMessageId());
        ReplyKeyboardHide replyKeyboardHide = new ReplyKeyboardHide();
        replyKeyboardHide.setHideKeyboard(true);
        replyKeyboardHide.setSelective(true);
        sendMessageRequest.setReplayMarkup(replyKeyboardHide);

        sendMessageRequest.setText("Circuito: "+cir.getName());

        try {
            sendMessage(sendMessageRequest);
            sendPhoto(p1);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    private void PilotoRecibido(Message message)throws InvalidObjectException {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId().toString());
        //Creamos circuitos

        SendPhoto p1 = new SendPhoto();
        p1.setPhoto(Circuito.id_barcelona);
        p1.setChatId(message.getChatId() + "");

        Circuito cir = new Circuito("Barcelona");

        sendMessageRequest.setReplayToMessageId(message.getMessageId());
        ReplyKeyboardHide replyKeyboardHide = new ReplyKeyboardHide();
        replyKeyboardHide.setHideKeyboard(true);
        replyKeyboardHide.setSelective(true);
        sendMessageRequest.setReplayMarkup(replyKeyboardHide);

        sendMessageRequest.setText("Circuito: "+cir.getName());

        try {
            sendMessage(sendMessageRequest);
            sendPhoto(p1);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }

    }

    public static void sendMenssage1(String m){
        //this.sendMessage(send);
        try {
            for (int i =0;i< Noticias.getIds().size();i++){
                SendMessage send = new SendMessage();
                send.setChatId(Noticias.ids.get(i));
                send.setText(m);
                try {

                    EchoHandlers a=new EchoHandlers();

                    a.sendMessage(send);
                } catch (TelegramApiException e) {
                    System.out.println(Noticias.ids.get(i)+"\n"+e.getCause().toString());
                    e.printStackTrace();

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void createKeyEscuderia(Message m){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setReplayToMessageId(m.getMessageId());
        sendMessageRequest.setChatId(m.getChatId().toString());
        sendMessageRequest.enableMarkdown(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<List<String>> keyboard = new ArrayList<>();
        List<String> keyboardFirstRow = new ArrayList<>();
        keyboardFirstRow.add(Escuderias.getNameEscuderias().get(0));
        keyboardFirstRow.add(Escuderias.getNameEscuderias().get(1));
        List<String> keyboard2Row = new ArrayList<>();
        keyboard2Row.add(Escuderias.getNameEscuderias().get(2));
        keyboard2Row.add(Escuderias.getNameEscuderias().get(3));
        List<String> keyboard3Row = new ArrayList<>();
        keyboard3Row.add(Escuderias.getNameEscuderias().get(4));
        keyboard3Row.add(Escuderias.getNameEscuderias().get(5));
        List<String> keyboard4Row = new ArrayList<>();
        keyboard4Row.add(Escuderias.getNameEscuderias().get(6));
        keyboard4Row.add(Escuderias.getNameEscuderias().get(7));
        List<String> keyboard5Row = new ArrayList<>();
        keyboard5Row.add(Escuderias.getNameEscuderias().get(8));
        keyboard5Row.add(Escuderias.getNameEscuderias().get(9));
        List<String> keyboard6Row = new ArrayList<>();
        keyboard6Row.add(Escuderias.getNameEscuderias().get(10));


        List<String> keyboardCancelRow = new ArrayList<>();
        keyboardCancelRow.add("Cancelar");


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboard2Row);
        keyboard.add(keyboard3Row);
        keyboard.add(keyboard4Row);
        keyboard.add(keyboard5Row);
        keyboard.add(keyboard6Row);



        keyboard.add(keyboardCancelRow);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        sendMessageRequest.setReplayMarkup(replyKeyboardMarkup);
        sendMessageRequest.setText("Elige que Escuderia deseas recibir");
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    public void setGP(Message message){
        String namecircuit=message.getText().substring(0,message.getText().indexOf("-"));
        SendMessage sendMessageRequest = new SendMessage();
        SendPhoto image = new SendPhoto();
        switch (namecircuit){
            case "GP Australia ":
                image.setPhoto(Circuito.id_Australia);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Australia());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Baréin ":
                image.setPhoto(Circuito.id_Barein);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Barein());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP China ":
                image.setPhoto(Circuito.id_China);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.China());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Rusia ":
                image.setPhoto(Circuito.id_Rusia);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Rusia());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP España ":
                image.setPhoto(Circuito.id_barcelona);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.España());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Mónaco ":
                image.setPhoto(Circuito.id_Monaco);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Monaco());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Canadá ":
                image.setPhoto(Circuito.id_Canada);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Canada());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Europa ":
                image.setPhoto(Circuito.id_Europa);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Europa());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Austria ":
                image.setPhoto(Circuito.id_Austria);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Austria());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Gran Bretaña ":
                image.setPhoto(Circuito.id_GranBretaña);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.GranBretaña());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Hungria ":
                image.setPhoto(Circuito.id_Hungria);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Hungria());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Alemania ":
                image.setPhoto(Circuito.id_Alemania);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Alemania());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Bélgica ":
                image.setPhoto(Circuito.id_Belgica);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Belgica());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Italia ":
                image.setPhoto(Circuito.id_Italia);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Italia());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Singapur ":
                image.setPhoto(Circuito.id_Singapur);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Singapur());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Malasia ":
                image.setPhoto(Circuito.id_Malasia);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Malasia());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Japón ":
                image.setPhoto(Circuito.id_Japon);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Japon());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP EEUU ":
                image.setPhoto(Circuito.id_EEUU);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.EEUU());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Mexico ":
                image.setPhoto(Circuito.id_Mexico);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Mexico());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Brasil ":
                image.setPhoto(Circuito.id_Brasil);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.Brasil());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GP Abu Dabi ":
                image.setPhoto(Circuito.id_AbuDabi);
                image.setChatId(message.getChatId() + "");
                sendMessageRequest.setChatId(message.getChatId() + "");
                sendMessageRequest.setText(Circuito.AbuDabi());
                try {
                    sendMessage(sendMessageRequest);
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            default: break;

        }
    }

    public void setPiloto(Message message){
        String namepiloto=message.getText().substring(0,message.getText().indexOf(" -"));
        SendPhoto image = new SendPhoto();
        image.setChatId(message.getChatId() + "");
        switch (namepiloto) {
            case "Dorsal: 6":
                try {
                    sendMessage(Pilotos.getmessagePilotos("6",message));
                    image.setPhoto("AgADBAAD76cxG8pmggl6o9bWrDRIa-07JBkABFRMwIXkePU9o1oAAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                    System.out.println(e);
                }
                break;
            case "Dorsal: 44":
                try {
                    sendMessage(Pilotos.getmessagePilotos("44",message));
                    image.setPhoto("AgADBAAD8KcxG8pmgglmB0c6qgrtdIgKGxkABKQKFddqHQoj62wAAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 5":
                try {
                    sendMessage(Pilotos.getmessagePilotos("5",message));
                    image.setPhoto("AgADBAAD7qcxG8pmggnjrM5SrxpjaW8qGxkABLS_tGmZhJYVVGwAAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 7":
                try {
                    sendMessage(Pilotos.getmessagePilotos("7",message));
                    image.setPhoto("AgADBAAD86cxG8pmggnmlFg0f1LuFFJ2GxkABHdzfOlqbpYsR74BAAEC");
                    sendPhoto(image);                    } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 19":
                try {
                    sendMessage(Pilotos.getmessagePilotos("19",message));
                    image.setPhoto("AgADBAAD9KcxG8pmggkxmICukt7um-T5HBkABM6Hge8WVha6_20AAgI");
                    sendPhoto(image);                    } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 77":
                try {
                    sendMessage(Pilotos.getmessagePilotos("77",message));
                    image.setPhoto("AgADBAAD9acxG8pmggll-j3NMCnBAfLBJRkABGO_3nkqI56MaFoAAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 3":
                try {
                    sendMessage(Pilotos.getmessagePilotos("3",message));
                    image.setPhoto("AgADBAAD96cxG8pmggn8E8BDEN-vEDEnHRkABHXb0ka4vs4DzbwBAAEC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 26":
                try {
                    sendMessage(Pilotos.getmessagePilotos("26",message));
                    image.setPhoto("AgADBAAD-KcxG8pmgglUqHQw6iMAAXj3PBsZAAQjQTx4dofthiV-AAIC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 11":
                try {
                    sendMessage(Pilotos.getmessagePilotos("11",message));
                    image.setPhoto("AgADBAAD-acxG8pmggnYcYYbcNtqH08SHRkABItk0krA87kxAAFvAAIC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 27":
                try {
                    sendMessage(Pilotos.getmessagePilotos("27",message));
                    image.setPhoto("AgADBAAD-qcxG8pmggk4IuiqPcNKl4qZHBkABP0IXBQQ2U_VsbgBAAEC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 20":
                try {
                    sendMessage(Pilotos.getmessagePilotos("20",message));
                    image.setPhoto("AgADBAAD-6cxG8pmggmSscj3RXzuMPD4JRkABCrb-31Zl2Z86FwAAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 30":
                try {
                    sendMessage(Pilotos.getmessagePilotos("30",message));
                    image.setPhoto("AgADBAAD_acxG8pmggm0l1FXeVmivvqUHBkABFUfxnEdh1yPR7sBAAEC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 55":
                try {
                    sendMessage(Pilotos.getmessagePilotos("55",message));
                    image.setPhoto("AgADBAADvrIxG8JXLgABri2MfdNpjzPodRsZAASE3MK8hrhL1ea9AQABAg");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 33":
                try {
                    sendMessage(Pilotos.getmessagePilotos("33",message));

                    image.setPhoto("AgADBAADv7IxG8JXLgABdFTgWAPawhnJKhsZAASEiK8EucNlsz9vAAIC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 9":
                try {
                    sendMessage(Pilotos.getmessagePilotos("9",message));
                    image.setPhoto("AgADBAAD_qcxG8pmgglq2eQvwGdUlX3CHBkABHcRUzB7vwtQXsABAAEC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 12":
                try {
                    sendMessage(Pilotos.getmessagePilotos("12",message));
                    image.setPhoto("AgADBAAD_6cxG8pmggmn5ifaGBBDW9UsGxkABDxSCN2ih7eDFH0AAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 14":
                try {
                    sendMessage(Pilotos.getmessagePilotos("14",message));
                    image.setPhoto("AgADBAAEqDEbymaCCV4Xj3SqEW1WI08bGQAEhuD15b7KBOsMvQEAAQI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 22":
                try {
                    sendMessage(Pilotos.getmessagePilotos("22",message));
                    image.setPhoto("AgADBAADAqgxG8pmggkteOwDi1jq4LoiHRkABJwc3sMpURaWzG0AAgI");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 94":
                try {
                    sendMessage(Pilotos.getmessagePilotos("94",message));
                    image.setPhoto("AgADBAADxrIxG8JXLgABbJosIvVk3wwI_xwZAAQ-x60cyDs0XuxyAAIC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 8":
                try {
                    sendMessage(Pilotos.getmessagePilotos("8",message));
                    image.setPhoto("AgADBAADwLIxG8JXLgABaA2ylXTBQO3TfCQZAAQ6IoRJDHgWB6NaAAIC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "Dorsal: 21":
                try {
                    sendMessage(Pilotos.getmessagePilotos("21",message));
                    image.setPhoto("AgADBAADwbIxG8JXLgACl8N3PMCGTCnkHBkABJG3xQxI2arzor0BAAEC");
                    sendPhoto(image);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            default:
                break;
        }
    }

    public void setEscuderia(Message message){
        String nameescuderia=message.getText().substring(message.getText().indexOf("- "));
        SendPhoto image = new SendPhoto();
        image.setChatId(message.getChatId() + "");

        switch (nameescuderia) {
            case "- Mercedes":
                try {
                    image.setPhoto("AgADBAADyrIxG8JXLgABCBkVkdjyFsz48SUZAASPGCuePzelNu5iAAIC");
                    sendMessage(Escuderias.getmessageEscuderia("1", message));
                    sendPhoto(Escuderias.getphoto("1", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Ferrari":
                try {
                    image.setPhoto("AgADBAADy7IxG8JXLgABLDikOgudA2LWDR0ZAAS4_m3dlkOl2CXFAQABAg");
                    sendMessage(Escuderias.getmessageEscuderia("2", message));
                    sendPhoto(Escuderias.getphoto("2", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Williams":
                try {
                    image.setPhoto("AgADBAADzLIxG8JXLgABGm1LqippGiZbtRwZAARiH1_DNo4_SODIAQABAg");
                    sendMessage(Escuderias.getmessageEscuderia("1", message));
                    sendPhoto(Escuderias.getphoto("3", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Red Bull":
                try {
                    image.setPhoto("AgADBAADzbIxG8JXLgABOQYjsdRDiLeO8CUZAAS8bbow4CWR8hhkAAIC");
                    sendMessage(Escuderias.getmessageEscuderia("4", message));
                    sendPhoto(Escuderias.getphoto("4", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Force India":
                try {
                    image.setPhoto("AgADBAADzrIxG8JXLgABTtFQpLDyhtI7CxsZAARMlHJKWE6nnhZ0AAIC");
                    sendMessage(Escuderias.getmessageEscuderia("5", message));
                    sendPhoto(Escuderias.getphoto("5", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Renault":
                try {
                    image.setPhoto("AgADBAADz7IxG8JXLgABoyd_hcoDlVfUciQZAARLkBBykRwpygRkAAIC");
                    sendMessage(Escuderias.getmessageEscuderia("6", message));
                    sendPhoto(Escuderias.getphoto("6", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Toro Rosso":
                try {
                    image.setPhoto("AgADBAAD0LIxG8JXLgABykTQEkqHF2kGAR0ZAASh1FUd8WieI4F1AAIC");
                    sendMessage(Escuderias.getmessageEscuderia("7", message));
                    sendPhoto(Escuderias.getphoto("7", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Sauber":
                try {
                    image.setPhoto("AgADBAAD0bIxG8JXLgAB85FQ1L9C2woe6hwZAAQGNwABk_A3vylqxQEAAQI");
                    sendMessage(Escuderias.getmessageEscuderia("8", message));
                    sendPhoto(Escuderias.getphoto("8", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Mclaren":
                try {
                    image.setPhoto("AgADBAAD07IxG8JXLgABUOG5rcFv5dH6fCQZAATnA-6l9TA0CDhkAAIC");
                    sendMessage(Escuderias.getmessageEscuderia("9", message));
                    sendPhoto(Escuderias.getphoto("9", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Manor":
                try {
                    image.setPhoto("AgADBAAD1LIxG8JXLgABNNISKzQAAffIshobGQAEWjrLcp537hwzeAACAg");
                    sendMessage(Escuderias.getmessageEscuderia("10", message));
                    sendPhoto(Escuderias.getphoto("10", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            case "- Haas":
                try {
                    image.setPhoto("AgADBAAD1bIxG8JXLgABdNIqc06I2aGghxsZAAQL0eyjRyeLeqPHAQABAg");
                    sendMessage(Escuderias.getmessageEscuderia("11", message));
                    sendPhoto(Escuderias.getphoto("11", message));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
                break;
            default:
                break;
        }
    }

    public void createKeyPilotos(Message message){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setReplayToMessageId(message.getMessageId());
        sendMessageRequest.setChatId(message.getChatId().toString());
        sendMessageRequest.enableMarkdown(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<List<String>> keyboard = new ArrayList<>();
        List<String> keyboardFirstRow = new ArrayList<>();
        keyboardFirstRow.add(Pilotos.getNamePilotos().get(0));
        keyboardFirstRow.add(Pilotos.getNamePilotos().get(1));
        List<String> keyboard2Row = new ArrayList<>();
        keyboard2Row.add(Pilotos.getNamePilotos().get(2));
        keyboard2Row.add(Pilotos.getNamePilotos().get(3));
        List<String> keyboard3Row = new ArrayList<>();
        keyboard3Row.add(Pilotos.getNamePilotos().get(4));
        keyboard3Row.add(Pilotos.getNamePilotos().get(5));
        List<String> keyboard4Row = new ArrayList<>();
        keyboard4Row.add(Pilotos.getNamePilotos().get(6));
        keyboard4Row.add(Pilotos.getNamePilotos().get(7));
        List<String> keyboard5Row = new ArrayList<>();
        keyboard5Row.add(Pilotos.getNamePilotos().get(8));
        keyboard5Row.add(Pilotos.getNamePilotos().get(9));
        List<String> keyboard6Row = new ArrayList<>();
        keyboard6Row.add(Pilotos.getNamePilotos().get(10));
        keyboard6Row.add(Pilotos.getNamePilotos().get(11));
        List<String> keyboard7Row = new ArrayList<>();
        keyboard7Row.add(Pilotos.getNamePilotos().get(12));
        keyboard7Row.add(Pilotos.getNamePilotos().get(13));
        List<String> keyboard8Row = new ArrayList<>();
        keyboard8Row.add(Pilotos.getNamePilotos().get(14));
        keyboard8Row.add(Pilotos.getNamePilotos().get(15));
        List<String> keyboard9Row = new ArrayList<>();
        keyboard9Row.add(Pilotos.getNamePilotos().get(16));
        keyboard9Row.add(Pilotos.getNamePilotos().get(17));
        List<String> keyboard10Row = new ArrayList<>();
        keyboard10Row.add(Pilotos.getNamePilotos().get(18));
        keyboard10Row.add(Pilotos.getNamePilotos().get(19));
        List<String> keyboard11Row = new ArrayList<>();
        keyboard11Row.add(Pilotos.getNamePilotos().get(20));
        keyboard11Row.add(Pilotos.getNamePilotos().get(21));


        List<String> keyboardCancelRow = new ArrayList<>();
        keyboardCancelRow.add("Cancelar");


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboard2Row);
        keyboard.add(keyboard3Row);
        keyboard.add(keyboard4Row);
        keyboard.add(keyboard5Row);
        keyboard.add(keyboard6Row);
        keyboard.add(keyboard7Row);
        keyboard.add(keyboard8Row);
        keyboard.add(keyboard9Row);
        keyboard.add(keyboard10Row);
        keyboard.add(keyboard11Row);


        keyboard.add(keyboardCancelRow);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        sendMessageRequest.setReplayMarkup(replyKeyboardMarkup);
        sendMessageRequest.setText("Elige que Piloto deseas recibir");
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    public void createKeyCircuitos(Message message){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setReplayToMessageId(message.getMessageId());
        sendMessageRequest.setChatId(message.getChatId().toString());
        sendMessageRequest.enableMarkdown(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<List<String>> keyboard = new ArrayList<>();
        List<String> keyboardFirstRow = new ArrayList<>();
        keyboardFirstRow.add(Circuito.getCircuitos().get(0));
        keyboardFirstRow.add(Circuito.getCircuitos().get(1));
        keyboardFirstRow.add(Circuito.getCircuitos().get(2));
        List<String> keyboard2Row = new ArrayList<>();
        keyboard2Row.add(Circuito.getCircuitos().get(3));
        keyboard2Row.add(Circuito.getCircuitos().get(4));
        keyboard2Row.add(Circuito.getCircuitos().get(5));
        List<String> keyboard3Row = new ArrayList<>();
        keyboard3Row.add(Circuito.getCircuitos().get(6));
        keyboard3Row.add(Circuito.getCircuitos().get(7));
        keyboard3Row.add(Circuito.getCircuitos().get(8));
        List<String> keyboard4Row = new ArrayList<>();
        keyboard4Row.add(Circuito.getCircuitos().get(9));
        keyboard4Row.add(Circuito.getCircuitos().get(10));
        keyboard4Row.add(Circuito.getCircuitos().get(11));
        List<String> keyboard5Row = new ArrayList<>();
        keyboard5Row.add(Circuito.getCircuitos().get(12));
        keyboard5Row.add(Circuito.getCircuitos().get(13));
        keyboard5Row.add(Circuito.getCircuitos().get(14));
        List<String> keyboard6Row = new ArrayList<>();
        keyboard6Row.add(Circuito.getCircuitos().get(15));
        keyboard6Row.add(Circuito.getCircuitos().get(16));
        keyboard6Row.add(Circuito.getCircuitos().get(17));
        List<String> keyboard7Row = new ArrayList<>();
        keyboard7Row.add(Circuito.getCircuitos().get(18));
        keyboard7Row.add(Circuito.getCircuitos().get(19));
        keyboard7Row.add(Circuito.getCircuitos().get(20));
        List<String> keyboard8Row = new ArrayList<>();
        keyboard8Row.add("Cancelar");


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboard2Row);
        keyboard.add(keyboard3Row);
        keyboard.add(keyboard4Row);
        keyboard.add(keyboard5Row);
        keyboard.add(keyboard6Row);
        keyboard.add(keyboard7Row);
        keyboard.add(keyboard8Row);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        sendMessageRequest.setReplayMarkup(replyKeyboardMarkup);
        sendMessageRequest.setText("Elige que Circuito deseas recibir");
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}