package com.delozoya.services;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.SendMessage;
import com.delozoya.updateshandlers.EchoHandlers;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by David on 11/02/2016.
 */
public class Noticias {

    public static int cont;
    public static boolean repeat;
    public static ArrayList<String> ids = new ArrayList<String>();
    public Noticias(){}

    public static ArrayList<String> getIds() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://62.43.193.254:3306/id_mensajes", "DbF1", "f1");
        /*Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from id_mensajes.id ");*/

        String SQLEjecutar ="select * from id_mensajes.id ";
        String resultadoSQL = "";
        ids.clear();
        repeat=false;
        //ejecutamos consulta SQL de selección (devuelve datos)
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(SQLEjecutar);

        int numColumnas = rs.getMetaData().getColumnCount();

       System.out.println(numColumnas);



        //mostramos el resultado
        while (rs.next())
        {
            //obtenemos el título de las columnas
           /*for (int i = 1; i <= numColumnas; i++)
            {
                if (resultadoSQL != "")
                    if (i < numColumnas)
                        resultadoSQL = resultadoSQL +
                                rs.getMetaData().getColumnName(i).toString() + ";";
                    else
                        resultadoSQL = resultadoSQL +
                                rs.getMetaData().getColumnName(i).toString();
                else
                if (i < numColumnas)
                    resultadoSQL =
                            rs.getMetaData().getColumnName(i).toString() + ";";
                else
                    resultadoSQL =
                            rs.getMetaData().getColumnName(i).toString();

            }*/
            resultadoSQL = resultadoSQL + " ";
            //obtenemos los datos de cada columna
            for (int i = 1; i <= numColumnas; i++)
            {
                if (rs.getObject(i) != null) {

                    if (resultadoSQL != "") {
                        if (i < numColumnas) {
                            resultadoSQL = resultadoSQL +
                                    rs.getObject(i).toString() + ";";
                            ids.add(rs.getObject(i).toString());
                        } else {
                            resultadoSQL = resultadoSQL +
                                    rs.getObject(i).toString();
                            ids.add(rs.getObject(i).toString());
                        }
                    }else {
                        if (i < numColumnas) {
                            resultadoSQL = rs.getObject(i).toString() + ";";
                            ids.add(rs.getObject(i).toString());
                        }else {
                            resultadoSQL = rs.getObject(i).toString();
                            ids.add(rs.getObject(i).toString());
                        }
                    }
                }else{
                    if (resultadoSQL != "") {
                        resultadoSQL = resultadoSQL + "null;";
                        ids.add(rs.getObject(i).toString());
                    }else {
                        resultadoSQL = "null;";
                    }
                }
            }
            resultadoSQL = resultadoSQL + " ";
        }
        System.out.println(resultadoSQL);


        st.close();
        rs.close();
        return ids;
    }

    public static void addID(String id) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://62.43.193.254:3306/id_mensajes", "DbF1", "f1");
        /*Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from id_mensajes.id ");*/

        getIds();
        //ejecutamos consulta SQL de selección (devuelve datos)
        Statement st = conexion.createStatement();


        for(int j =0;j<ids.size();j++){
            System.out.println(ids.get(j));
            if(ids.get(j).equals(String.valueOf(id))){
                System.out.println("serepite");
                repeat=true;
            }
        }
        if(repeat){
            EchoHandlers a = new EchoHandlers();
            SendMessage b = new SendMessage();
            b.setChatId(id);
            b.setText("Ya estas subcrito a las noticias.\n" +
                    "Pasa no recibir mas noticias usa el comando /stopnoticias.");
            try {
                a.sendMessage(b);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {
            st.executeUpdate("INSERT INTO id_mensajes.id VALUES ("+id+");");
            EchoHandlers a = new EchoHandlers();
            SendMessage b = new SendMessage();
            b.setChatId(id);
            b.setText("Gracias por suscribirse a las Noticias de NewsF1Bot. \n" +
                    "A partir de ahora le llegaran Mensajes con los ultimos tweets sobre la F1.\n" +
                    "Para no recibir mas noticias usa el comando /stopnoticias.");
            try {
                a.sendMessage(b);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }







}
