package com.delozoya.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 04/02/2016.
 */
public class Circuito {

    String name;


    public static String id_Australia="AgADBAADdrIxG8JXLgAB_kaWZXnzg_6TQBsZAARxmqbR4twglTdDAAIC";

    public static String id_Barein ="AgADBAADd7IxG8JXLgAB0WblTRoh2i_xGh0ZAARfj_L2UwQinlQ0AAIC";

    public static String id_China ="AgADBAADeLIxG8JXLgABM81rQJN_qG61kxsZAASkeWNO-ot8u52IAQABAg";

    public static String id_Rusia ="AgADBAADebIxG8JXLgAB7-3DjJQ0i4gwvKYwAATEYSkLYHftdS9pAgABAg";

    public static String id_barcelona="AgADBAADXLIxG8JXLgABxegUtFkqo249lhwZAAR5oC69gz7b_oZ2AQABAg";

    public static String id_Monaco ="AgADBAADerIxG8JXLgABnDDAUzcU8mRxAR0ZAATtmeUNUJUf3JUzAAIC";

    public static String id_Canada ="AgADBAADe7IxG8JXLgABTPGZHjTclZttkaYwAATd3RZe_4WdnTZ0AgABAg";

    public static String id_Europa ="AgADBAADfLIxG8JXLgAB1Lx9qXREWid8CBsZAAT4MWXxg0ECBVszAAIC";

    public static String id_Austria ="AgADBAADfbIxG8JXLgABuB0TnAlkK7h-9SUZAATGTgEK2O9qyr8hAAIC";

    public static String id_GranBretaña ="AgADBAADfrIxG8JXLgABGWOQIzrCDAPS9SUZAAT-CZbYoHoag9ogAAIC";

    public static String id_Hungria ="AgADBAADf7IxG8JXLgABoFur57P8Lmb1Cx0ZAASBMt3O1TsTni-JAQABAg";

    public static String id_Alemania ="AgADBAADgLIxG8JXLgAB2rGDYTaF453gHRsZAAQ5BXv4cKKrsBU1AAIC";

    public static String id_Belgica ="AgADBAADgbIxG8JXLgAB8JLQaGKEV1X0_SUZAATh76iXhaV26gQgAAIC";

    public static String id_Italia ="AgADBAADgrIxG8JXLgABOuyCTaNQZvBQ5xwZAATb-wqHBZq5MXuFAQABAg";

    public static String id_Singapur ="AgADBAADg7IxG8JXLgABVK0NfXKRpisBEhsZAAT6aVOtxo-HHmRGAAIC";

    public static String id_Malasia ="AgADBAADhLIxG8JXLgABTgWSmEehz-du8KEwAARRjIt-lsj1VnpxAgABAg";

    public static String id_Japon ="AgADBAADhbIxG8JXLgABl7kNOLp8LjM_kyQZAAQoUPmkefdQvPsgAAIC";

    public static String id_EEUU ="AgADBAADhrIxG8JXLgABGbXhKAABubWrhUKmMAAENijeQeyDvdDQaQIAAQI";

    public static String id_Mexico ="AgADBAADh7IxG8JXLgABrbgk4ezOwcPWmRwZAAQSMVf4H2SmJ5KDAQABAg";

    public static String id_Brasil ="AgADBAADiLIxG8JXLgABFQbP8B2dgt1kKRsZAARBhgABTdgEx8YNNAACAg";

    public static String id_AbuDabi ="AgADBAADibIxG8JXLgABfayu08tK6HzpPxsZAASmBNrC02x_LLNGAAIC";


    private static final ArrayList<String> circuitos = new ArrayList<String>();

    private static final String[][] allCircuits = new String[10][2];


    public static List<List<String>> getkeyboardCircuits() {
        List<List<String>> keyboard = new ArrayList<>();
        List<String> keyboardFirstRow = new ArrayList<>();
        keyboardFirstRow.add(circuitos.get(0));
        keyboardFirstRow.add(circuitos.get(1));
        List<String> keyboardSecondRow = new ArrayList<>();
        keyboardSecondRow.add(circuitos.get(2));
        keyboardSecondRow.add(circuitos.get(3));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        return keyboard;
    }

    public Circuito(){

    }

    public static String Australia(){
        return "Albert Park\n"
                +"Longuitud: 5'303 km\n"
                +"Número de vueltas: 58"
                +"Curvas: 16\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Vettel, 2011 - 1'23\"529\n"
                +"Récord vuelta rapida: Schumacher, 2004 - 1'24\"125\n"
                +"Fecha Carrera: 20 Marzo 2016\n"
                +"Hora Inicio (España): 07:00h";
    }

    public static String Barein(){
        return "Sakir\n"
                +"Longuitud: 5'412 km\n"
                +"Número de vueltas: 57\n"
                +"Curvas: 15\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: -\n"
                +"Récord vuelta rapida: -\n"
                +"Fecha Carrera: 3 Abril 2016\n"
                +"Hora Inicio (España): 17:00h";
    }

    public static String China(){
        return "Shanghai\n"
                +"Longuitud: 5'451 km\n"
                +"Número de vueltas: 56\n"
                +"Curvas: 16\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Vettel, 2011 - 1'33\"706\n"
                +"Récord vuelta rapida: Schumacher, 2004 - 1'32\"238\n"
                +"Fecha Carrera: 17 Abril 2016\n"
                +"Hora Inicio (España): 08:00h";
    }

    public static String Rusia(){
        return "Sochi\n"
                +"Longuitud: 5'848 km\n"
                +"Número de vueltas: 53\n"
                +"Curvas: 19\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Rosberg, 2015 - 1'37\"113\n"
                +"Récord vuelta rapida: Vettel, 2015 - 1'40\"071\n"
                +"Fecha Carrera: 1 Mayo 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String España(){
        return "Montmelo\n"
                +"Longuitud: 4'655 km\n"
                +"Número de vueltas: 66\n"
                +"Curvas: 16\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Nico Rosberg\n"
                +"Récord Pole: Webber, 2010 - 1'19\"995\n"
                +"Récord vuelta rapida: Räikkönen, 2008 - 1'21\"670\n"
                +"Fecha Carrera: 15 Mayo 2016\n"
                +"Hora Inicio (España): 08:00h";
    }

    public static String Monaco(){
        return "MonteCarlo\n"
                +"Longuitud: 3'340 km\n"
                +"Número de vueltas: 78\n"
                +"Curvas: 19\n"
                +"Zonas Drs: 1\n"
                +"Ultimo Ganador: Nico Rosberg\n"
                +"Récord Pole: Vettel, 2011 - 1'13\"556\n"
                +"Récord vuelta rapida: Schumacher, 2004 - 1'14\"439\n"
                +"Fecha Carrera: 29 Mayo 2016\n"
                +"Hora Inicio (España): 08:00h";
    }

    public static String Canada(){
        return "Gilles Villeneuve\n"
                +"Longuitud: 4'361 km\n"
                +"Número de vueltas: 70\n"
                +"Curvas: 12\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: R.Schumacher, 2004 - 1'12\"275\n"
                +"Récord vuelta rapida: Barrichello, 2004 - 1'13\"622\n"
                +"Fecha Carrera: 12 junio 2016\n"
                +"Hora Inicio (España): 20:00h";
    }

    public static String Europa(){
        return "Baku\n"
                +"Longuitud: 6'006 km\n"
                +"Número de vueltas: -\n"
                +"Curvas: 20\n"
                +"Zonas Drs: -\n"
                +"Ultimo Ganador: -\n"
                +"Récord Pole: -\n"
                +"Récord vuelta rapida: -\n"
                +"Fecha Carrera: 19 junio 2016\n"
                +"Hora Inicio (España): 15:00h";
    }

    public static String Austria(){
        return "A1-Ring Spielberg (Red Bull Ring)\n"
                +"Longuitud: 4'326 km\n"
                +"Número de vueltas: 71\n"
                +"Curvas: 9\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Nico Rosberg\n"
                +"Récord Pole: Barrichello, 2002 - 1'08\"082\n"
                +"Récord vuelta rapida: Schumacher, 2003 - 1'08\"337\n"
                +"Fecha Carrera: 3 Julio 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String GranBretaña(){
        return "Silverstone\n"
                +"Longuitud: 5'891 km\n"
                +"Número de vueltas: 52\n"
                +"Curvas: 18\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Hamilton, 2013 - 1'29\"607\n"
                +"Récord vuelta rapida: Webber, 2013 - 1'33\"401\n"
                +"Fecha Carrera: 10 Julio 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String Hungria(){
        return "Hungaroring\n"
                +"Longuitud: 4'381 km\n"
                +"Número de vueltas: 70\n"
                +"Curvas: 14\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Sebastian Vettel\n"
                +"Récord Pole: Vettel, 2010 - 1'18\"773\n"
                +"Récord vuelta rapida: Schumacher, 2004 - 1'19\"071\n"
                +"Fecha Carrera: 24 Julio 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String Alemania(){
        return "Hockenheim\n"
                +"Longuitud: 4'574 km\n"
                +"Número de vueltas: 67\n"
                +"Curvas: 13\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Nico Rosberg (2014)\n"
                +"Récord Pole: Schumacher, 2004 - 1'13\"306\n"
                +"Récord vuelta rapida: Räikkönen, 2004 - 1'13\"780\n"
                +"Fecha Carrera: 31 Julio 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String Belgica(){
        return "Spa-Francorchamps\n"
                +"Longuitud: 7'004 km\n"
                +"Número de vueltas: 44\n"
                +"Curvas: 19\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Webber, 2010 - 1'45\"778\n"
                +"Récord vuelta rapida: Vettel, 2009 - 1'47\"263\n"
                +"Fecha Carrera: 28 Agosto 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String Italia(){
        return "Monza\n"
                +"Longuitud: 5'793 km\n"
                +"Número de vueltas: 53\n"
                +"Curvas: 11\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Barrichello, 2004 - 1'20\"089\n"
                +"Récord vuelta rapida: Barrichello, 2004 - 1'21\"046\n"
                +"Fecha Carrera: 4 Septiembre 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String Singapur(){
        return "Marian Bay\n"
                +"Longuitud: 5'065 km\n"
                +"Número de vueltas: 61\n"
                +"Curvas: 23\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Sebastian Vettel\n"
                +"Récord Pole: Vettel, 2013 - 1'42\"841\n"
                +"Récord vuelta rapida: Vettel, 2013 - 1'48\"574\n"
                +"Fecha Carrera: 18 Septiembre 2016\n"
                +"Hora Inicio (España): 14:00h";
    }

    public static String Japon(){
        return "Suzuka\n"
                +"Longuitud: 5'807 km\n"
                +"Número de vueltas: 53\n"
                +"Curvas: 18\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Massa, 2006 - 1'29\"599\n"
                +"Récord vuelta rapida: Räikkönen, 2005 - 1'31\"540\n"
                +"Fecha Carrera: 9 Octubre 2016\n"
                +"Hora Inicio (España): 07:00h";
    }

    public static String EEUU(){
        return "Austin (Por Confirmar)\n"
                +"Longuitud: 5'513 km\n"
                +"Número de vueltas: 56\n"
                +"Curvas: 20\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Lewis Hamilton\n"
                +"Récord Pole: Vettel, 2012 - 1'35\"657\n"
                +"Récord vuelta rapida: Vettel, 2012 - 1'39\"347\n"
                +"Fecha Carrera: 23 Octubre 2016\n"
                +"Hora Inicio (España): 21:00h";
    }

    public static String Mexico(){
        return "Shanghai\n"
                +"Longuitud: 4'304 km\n"
                +"Número de vueltas: 71\n"
                +"Curvas: 16\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Nico Rosberg\n"
                +"Récord Pole: Rosberg, 2015 - 1'19\"480\n"
                +"Récord vuelta rapida: Rosberg, 2015 - 1'20\"521\n"
                +"Fecha Carrera: 30 Octubre 2016\n"
                +"Hora Inicio (España): 20:00h";
    }

    public static String Brasil(){
        return "Interlagos\n"
                +"Longuitud: 4'309 km\n"
                +"Número de vueltas: 71\n"
                +"Curvas: 15\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Nico Rosberg\n"
                +"Récord Pole: Barrichello, 2004 - 1'10\"646\n"
                +"Récord vuelta rapida: Montoya, 2004 - 1'11\"347\n"
                +"Fecha Carrera: 13 Noviembre 2016\n"
                +"Hora Inicio (España): 17:00h";
    }

    public static String AbuDabi(){
        return "Yas Marina\n"
                +"Longuitud: 5'554 km\n"
                +"Número de vueltas: 55\n"
                +"Curvas: 21\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Nico Rosberg\n"
                +"Récord Pole: Vettel, 2011 - 1'38\"481\n"
                +"Récord vuelta rapida: Vettel, 2009 - 1'40\"279\n"
                +"Fecha Carrera: 27 Noviembre 2016\n"
                +"Hora Inicio (España): 14:00h";
    }



    public static String Malasia(){
        return "Sepang\n"
                +"Longuitud: 5'543 km\n"
                +"Número de vueltas: 56\n"
                +"Curvas: 15\n"
                +"Zonas Drs: 2\n"
                +"Ultimo Ganador: Sebastian Vettel\n"
                +"Récord pole: Schumacher, 2004 - 1'33\"074\n"
                +"Récord vuelta rapida: Montoya, 2004 - 1'34\"223\n"
                +"Fecha Carrera: 2 Octubre 2016\n"
                +"Hora Inicio (España): 09:00h";
    }

    public static ArrayList<String> getCircuitos(){
        circuitos.clear();
        circuitos.add("GP Australia - Albert Park");
        circuitos.add("GP Baréin - Sakhir");
        circuitos.add("GP China - Shanghái");
        circuitos.add("GP Rusia - Sochi");
        circuitos.add("GP España - Montmeló");
        circuitos.add("GP Mónaco - MónteCarlo");
        circuitos.add("GP Canadá - Gilles Villeneuve");
        circuitos.add("GP Europa - Bakú");
        circuitos.add("GP Austria - Red Bull Racing");
        circuitos.add("GP Gran Bretaña - Silverstone");
        circuitos.add("GP Hungria - Hungaroring");
        circuitos.add("GP Alemania - Hockenheim");
        circuitos.add("GP Bélgica - Spa");
        circuitos.add("GP Italia - Monza");
        circuitos.add("GP Singapur - Marina Bay");
        circuitos.add("GP Malasia - Sepang");
        circuitos.add("GP Japón - Suzuka");
        circuitos.add("GP EEUU - Austin");
        circuitos.add("GP Mexico - Hnos.Rodriguez");
        circuitos.add("GP Brasil - Interlagos");
        circuitos.add("GP Abu Dabi - Yas Marina");
        return circuitos;

    }



    public Circuito(String na){
        name = na;
    }

    public void setName(String name_){
        name = name_;
    }

    public String getName(){
        return name;
    }
}
