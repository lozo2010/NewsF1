package com.delozoya.services;


import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.methods.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by David on 09/02/2016.
 */
public class Pilotos {

    public static final String[] piloto= new String[30];
    private static final ArrayList<String> pilotos = new ArrayList<String>();



    public Pilotos(){



    }

    public static SendMessage getmessagePilotos(String name, Message mes){
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
        image.setNewPhoto("C:\\Users\\David\\Desktop\\pilotos\\"+name+".png",name+".png");
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
            ResultSet rs = s.executeQuery ("select * from pilotos.pilotos where Dorsal='"+name+"'");
            while (rs.next())
            {
            /*    System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getString(3)+ " "+rs.getString (4) + " " + rs.getString (5)+ " " + rs.getString(6)+ " "+rs.getInt (7) + " " + rs.getInt (8)+ " " + rs.getInt(9)+ " "+
                        rs.getInt (10) + " " + rs.getString (11)+ " " + rs.getInt(12)+ " "+rs.getInt (13) + " " + rs.getInt (14)+ " " + rs.getInt(15)+ " "+rs.getInt (16) + " " + rs.getInt (17)+ " " + rs.getString(18)+
                        " "+rs.getInt (19) + " " + rs.getString (20)+ " " + rs.getInt(21)+" "+rs.getInt(22));*/
                piloto[0]=rs.getString(2);
                piloto[1]=rs.getString(3);
                piloto[2]=rs.getString(4);
                piloto[3]=rs.getString(5);
                piloto[4]=rs.getString(6);
                piloto[5]=rs.getString(7);
                piloto[6]=rs.getString(8);
                piloto[7]=rs.getString(9);
                piloto[8]=rs.getString(10);
                piloto[9]=rs.getString(11);
                piloto[10]=rs.getString(12);
                piloto[11]=rs.getString(13);
                piloto[12]=rs.getString(14);
                piloto[13]=rs.getString(15);
                piloto[14]=rs.getString(16);
                piloto[15]=rs.getString(17);
                piloto[16]=rs.getString(18);
                piloto[17]=rs.getString(19);
                piloto[18]=rs.getString(20);
                piloto[19]=rs.getString(21);
                piloto[20]=rs.getString(22);
                piloto[21]=rs.getString(23);
            }
        String pi="Nombre: "+piloto[0]+" "+piloto[1]+"\n" +
                "Ciudad de Nacimiento: "+piloto[2]+"\n"+
                "Fecha de Nacimiento: "+piloto[3]+"\n"+
                "Mejor Posicion Mundial: "+piloto[4]+"\n"+
                "Numero de Poles: "+piloto[5]+"\n"+
                "Numero de Vueltas Rapidas: "+piloto[6]+"\n"+
                "Numero de Victorias: "+piloto[7]+"\n"+
                "Numero de Podiums: "+piloto[8]+"\n"+
                "Numero de Puntos Total: "+piloto[9]+"\n"+
                "Titulos: "+piloto[16]+"\n"+
                "Dorsal: "+piloto[17]+"\n"+
                "Escuderia: "+piloto[18]+"\n"+
                "Año Debut: "+piloto[19]+"\n"+
                "Numero de GP's Disputados: "+piloto[20]+"\n"+
                "--------Actual--------"+"\n"+
                "Posicion Mundial: "+piloto[10]+"\n"+
                "Poles: "+piloto[11]+"\n"+
                "Victorias: "+piloto[12]+"\n"+
                "Vueltas rapidas: "+piloto[13]+"\n"+
                "Podiums: "+piloto[14]+"\n"+
                "Puntos: "+piloto[15]+"\n" +
                "Abandonos: "+piloto[21]+"\n";
        //System.out.println(pi+"aodsnaojsn");
        return pi;


    }

    public static ArrayList<String> getNamePilotos(){
        pilotos.clear();
        pilotos.add("Dorsal: 6 - Nico Rosberg");
        pilotos.add("Dorsal: 44 - Lewis Hamilton");
        pilotos.add("Dorsal: 5 - Sebastian Vettel");
        pilotos.add("Dorsal: 7 - Kimi Räikkönen");
        pilotos.add("Dorsal: 19 - Felipe Massa");
        pilotos.add("Dorsal: 77 - Valtteri Bottas");
        pilotos.add("Dorsal: 3 - Daniel Ricciardo");
        pilotos.add("Dorsal: 26 - Daniil Kvyat");
        pilotos.add("Dorsal: 11 - Sergio Pérez");
        pilotos.add("Dorsal: 27 - Nico Hülkenberg");
        pilotos.add("Dorsal: 20 - Kevin Magnussen");
        pilotos.add("Dorsal: 30 - Jolyon Palmer");
        pilotos.add("Dorsal: 55 - Carlos Sainz");
        pilotos.add("Dorsal: 33 - Max Verstappen");
        pilotos.add("Dorsal: 9 - Marcus Ericsson");
        pilotos.add("Dorsal: 12 - Felipe Nasr");
        pilotos.add("Dorsal: 14 - Fernando Alonso");
        pilotos.add("Dorsal: 22 - Jenson Button");
        pilotos.add("Dorsal: 94 - Pascal Wehrlein");
        pilotos.add("Dorsal: - - -");
        pilotos.add("Dorsal: 8 - Romain Grosjean");
        pilotos.add("Dorsal: 21 - Esteban Gutiérrez");
        return pilotos;

    }

}
