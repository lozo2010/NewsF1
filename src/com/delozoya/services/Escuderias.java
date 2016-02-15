package com.delozoya.services;

import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.methods.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by David on 11/02/2016.
 */
public class Escuderias {

    public static final String[] escuderia= new String[30];
    private static final ArrayList<String> escuderias = new ArrayList<String>();

    public Escuderias(){

    }

    public static SendMessage getmessageEscuderia(String name, Message mes){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(mes.getChatId().toString());
        try {
            sendMessageRequest.setText(getPiloto(name));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return sendMessageRequest;
    }

    public static SendPhoto getphoto(String name, Message mes){
        SendPhoto image = new SendPhoto();
        image.setNewPhoto("C:\\Users\\David\\Desktop\\escuderias\\"+name+".jpg",name+".jpg");
        image.setChatId(mes.getChatId() + "");

        return image;
    }

    public static String getPiloto(String name) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://62.43.193.254:3306/pilotos", "DbF1", "f1");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery ("select * from escuderias.escuderias where ID='"+name+"'");
        while (rs.next())
        {
            /*    System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getString(3)+ " "+rs.getString (4) + " " + rs.getString (5)+ " " + rs.getString(6)+ " "+rs.getInt (7) + " " + rs.getInt (8)+ " " + rs.getInt(9)+ " "+
                        rs.getInt (10) + " " + rs.getString (11)+ " " + rs.getInt(12)+ " "+rs.getInt (13) + " " + rs.getInt (14)+ " " + rs.getInt(15)+ " "+rs.getInt (16) + " " + rs.getInt (17)+ " " + rs.getString(18)+
                        " "+rs.getInt (19) + " " + rs.getString (20)+ " " + rs.getInt(21)+" "+rs.getInt(22));*/
            escuderia[0]=rs.getString(2);
            escuderia[1]=rs.getString(3);
            escuderia[2]=rs.getString(4);
            escuderia[3]=rs.getString(5);
            escuderia[4]=rs.getString(6);
            escuderia[5]=rs.getString(7);
            escuderia[6]=rs.getString(8);
            escuderia[7]=rs.getString(9);
            escuderia[8]=rs.getString(10);
            escuderia[9]=rs.getString(11);
            escuderia[10]=rs.getString(12);
            escuderia[11]=rs.getString(13);
            escuderia[12]=rs.getString(14);
            escuderia[13]=rs.getString(15);
            escuderia[14]=rs.getString(16);
            escuderia[15]=rs.getString(17);

        }
        String pi="Nombre: "+escuderia[0]+"\n" +
                "Motor: "+escuderia[13]+"\n" +
                "Sede: "+escuderia[14]+"\n"+
                "Debut: "+escuderia[15]+"\n"+
                "Numero de Poles: "+escuderia[3]+"\n"+
                "Numero de Vuletas Rapidas: "+escuderia[4]+"\n"+
                "Numero de Victorias: "+escuderia[2]+"\n"+
                "Numero de Puntos Total: "+escuderia[5]+"\n"+
                "Titulos: "+escuderia[6]+"\n"+
                "Pilotos: "+escuderia[7]+"\n"+
                "Numero de GP's Disputados: "+escuderia[1]+"\n"+
                "--------Actual--------"+"\n"+
                "Posicion Mundial: "+escuderia[12]+"\n"+
                "Poles: "+escuderia[9]+"\n"+
                "Victorias: "+escuderia[8]+"\n"+
                "Vueltas rapidas: "+escuderia[10]+"\n"+
                "Puntos: "+escuderia[11]+"\n";
        //System.out.println(pi+"aodsnaojsn");
        return pi;


    }

    public static ArrayList<String> getNameEscuderias(){
        escuderias.clear();
        escuderias.add("Escuderia - Mercedes");
        escuderias.add("Escuderia - Ferrari");
        escuderias.add("Escuderia - Williams");
        escuderias.add("Escuderia - Red Bull");
        escuderias.add("Escuderia - Force India");
        escuderias.add("Escuderia - Renault");
        escuderias.add("Escuderia - Toro Rosso");
        escuderias.add("Escuderia - Sauber");
        escuderias.add("Escuderia - Mclaren");
        escuderias.add("Escuderia - Manor");
        escuderias.add("Escuderia - Haas");
        return escuderias;

    }
}
