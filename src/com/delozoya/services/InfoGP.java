package com.delozoya.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by David on 04/02/2016.
 */
public class InfoGP {

    public static String nextGP;

    static Calendar calenda_Australia = new GregorianCalendar(2016, Calendar.MARCH, 14);
    static Date date_Australia = new Date();
    static Calendar calenda_Barein = new GregorianCalendar(2016, Calendar.MARCH, 28);
    static Date date_Barein = new Date();
    static Calendar calenda_China = new GregorianCalendar(2016, Calendar.APRIL, 11);
    static Date date_China = new Date();
    static Calendar calenda_Rusia = new GregorianCalendar(2016, Calendar.APRIL, 25);
    static Date date_Rusia = new Date();
    static Calendar calenda_España = new GregorianCalendar(2016, Calendar.MAY, 9);
    static Date date_España = new Date();
    static Calendar calenda_Monaco = new GregorianCalendar(2016, Calendar.MAY, 23);
    static Date date_Monaco = new Date();
    static Calendar calenda_Canada = new GregorianCalendar(2016, Calendar.JUNE, 6);
    static Date date_Canada = new Date();
    static Calendar calenda_Europa = new GregorianCalendar(2016, Calendar.JUNE, 13);
    static Date date_Europa = new Date();
    static Calendar calenda_Austria = new GregorianCalendar(2016, Calendar.JUNE, 27);
    static Date date_Austria = new Date();
    static Calendar calenda_GranBretaña = new GregorianCalendar(2016, Calendar.JULY, 4);
    static Date date_GranBretaña = new Date();
    static Calendar calenda_Hungria = new GregorianCalendar(2016, Calendar.JULY, 18);
    static Date date_Hungria = new Date();
    static Calendar calenda_Alemania = new GregorianCalendar(2016, Calendar.JULY, 25);
    static Date date_Alemania = new Date();
    static Calendar calenda_Belgica = new GregorianCalendar(2016, Calendar.AUGUST, 22);
    static Date date_Belgica = new Date();
    static Calendar calenda_Italia = new GregorianCalendar(2016, Calendar.AUGUST, 29);
    static Date date_Italia = new Date();
    static Calendar calenda_Singapur = new GregorianCalendar(2016, Calendar.SEPTEMBER, 12);
    static Date date_Singapur = new Date();
    static Calendar calenda_Malasia = new GregorianCalendar(2016, Calendar.SEPTEMBER, 26);
    static Date date_Malasia = new Date();
    static Calendar calenda_Japon = new GregorianCalendar(2016, Calendar.OCTOBER, 3);
    static Date date_Japon = new Date();
    static Calendar calenda_EEUU = new GregorianCalendar(2016, Calendar.OCTOBER, 17);
    static Date date_EEUU = new Date();
    static Calendar calenda_Mexico = new GregorianCalendar(2016, Calendar.OCTOBER, 24);
    static Date date_Mexico = new Date();
    static Calendar calenda_Brasil = new GregorianCalendar(2016, Calendar.NOVEMBER, 7);
    static Date date_Brasil = new Date();
    static Calendar calenda_Abudabi = new GregorianCalendar(2016, Calendar.NOVEMBER, 21);
    static Date date_Abudabi = new Date();
    static Calendar calenda_fin = new GregorianCalendar(2016, Calendar.DECEMBER, 4);
    static Date date_fin = new Date();

    public static String getInfo(){
        date_Australia=calenda_Australia.getTime();
        date_Barein=calenda_Barein.getTime();
        date_China=calenda_China.getTime();
        date_Rusia=calenda_Rusia.getTime();
        date_España=calenda_España.getTime();
        date_Monaco=calenda_Monaco.getTime();
        date_Canada=calenda_Canada.getTime();
        date_Europa=calenda_Europa.getTime();
        date_Austria=calenda_Austria.getTime();
        date_GranBretaña=calenda_GranBretaña.getTime();
        date_Hungria=calenda_Hungria.getTime();
        date_Alemania=calenda_Alemania.getTime();
        date_Belgica=calenda_Belgica.getTime();
        date_Italia=calenda_Italia.getTime();
        date_Singapur=calenda_Singapur.getTime();
        date_Malasia=calenda_Malasia.getTime();
        date_Japon=calenda_Japon.getTime();
        date_EEUU=calenda_EEUU.getTime();
        date_Mexico=calenda_Mexico.getTime();
        date_Brasil=calenda_Brasil.getTime();
        date_Abudabi=calenda_Abudabi.getTime();
        date_fin=calenda_fin.getTime();
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistema=formateador.format(fechaActual);

        System.out.println(date_Australia.after(fechaActual)&&date_Australia.before(date_Barein));
        if (date_Australia.after(fechaActual)&&date_Australia.before(date_Barein)) {
            nextGP=Circuito.Barein();
            return Circuito.Australia()+"\nhttp://www.eltiempo.es/melbourne.html";
        }else if (date_Barein.after(fechaActual)&&date_Barein.before(date_China)){
            nextGP=Circuito.China();
            return Circuito.Barein()+"\nhttp://www.eltiempo.es/manama.html";
        }else if((date_China.after(fechaActual)&&date_China.before(date_Rusia))){
            nextGP=Circuito.Rusia();
            return Circuito.China()+"\nhttp://www.eltiempo.es/shanghai.html";
        }else if((date_Rusia.after(fechaActual)&&date_Rusia.before(date_España))){
            nextGP=Circuito.España();
            return Circuito.Rusia()+"\nhttp://www.eltiempo.es/sochi.html";
        }else if((date_España.after(fechaActual)&&date_España.before(date_Monaco))){
            nextGP=Circuito.Monaco();
            return Circuito.España()+"\nhttp://www.eltiempo.es/montmelo.html";
        }else if((date_Monaco.after(fechaActual)&&date_Monaco.before(date_Canada))){
            nextGP=Circuito.Canada();
            return Circuito.Monaco()+"\nhttp://www.eltiempo.es/montecarlo.html";
        }else if((date_Canada.after(fechaActual)&&date_Canada.before(date_Europa))){
            nextGP=Circuito.Europa();
            return Circuito.Canada()+"\nhttp://www.eltiempo.es/montreal.html";
        }else if((date_Europa.after(fechaActual)&&date_Europa.before(date_Austria))){
            nextGP=Circuito.Austria();
            return Circuito.Europa()+"\nhttp://www.eltiempo.es/baku.html";
        }else if((date_Austria.after(fechaActual)&&date_Austria.before(date_GranBretaña))){
            nextGP=Circuito.GranBretaña();
            return Circuito.Austria();
        }else if((date_GranBretaña.after(fechaActual)&&date_GranBretaña.before(date_Hungria))){
            nextGP=Circuito.Hungria();
            return Circuito.GranBretaña()+"\nhttp://www.eltiempo.es/silverstone.html";
        }else if((date_Hungria.after(fechaActual)&&date_Hungria.before(date_Alemania))){
            nextGP=Circuito.Alemania();
            return Circuito.Hungria()+"\nhttp://www.eltiempo.es/budapest.html";
        }else if((date_Alemania.after(fechaActual)&&date_Alemania.before(date_Belgica))){
            nextGP=Circuito.Belgica();
            return Circuito.Alemania()+"\nhttp://www.eltiempo.es/hockenheim.html";
        }else if((date_Belgica.after(fechaActual)&&date_Belgica.before(date_Italia))){
            nextGP=Circuito.Italia();
            return Circuito.Belgica()+"\nhttp://www.eltiempo.es/lieja.html";
        }else if((date_Italia.after(fechaActual)&&date_Italia.before(date_Singapur))){
            nextGP=Circuito.Singapur();
            return Circuito.Italia()+"\nhttp://www.eltiempo.es/monza.html";
        }else if((date_Singapur.after(fechaActual)&&date_Singapur.before(date_Malasia))){
            nextGP=Circuito.Malasia();
            return Circuito.Singapur()+"\nhttp://www.eltiempo.es/singapur.html";
        }else if((date_Malasia.after(fechaActual)&&date_Malasia.before(date_Japon))){
            nextGP=Circuito.Japon();
            return Circuito.Malasia()+"\nhttp://www.eltiempo.es/kuala-lumpur.html";
        }else if((date_Japon.after(fechaActual)&&date_Japon.before(date_EEUU))){
            nextGP=Circuito.EEUU();
            return Circuito.Japon()+"\nhttp://www.eltiempo.es/suzuka.html";
        }else if((date_EEUU.after(fechaActual)&&date_EEUU.before(date_Mexico))){
            nextGP=Circuito.Mexico();
            return Circuito.EEUU()+"\nhttp://www.eltiempo.es/austin.html";
        }else if((date_Mexico.after(fechaActual)&&date_Mexico.before(date_Brasil))){
            nextGP=Circuito.Brasil();
            return Circuito.Mexico()+"\nhttp://www.eltiempo.es/mexico.html";
        }else if((date_Brasil.after(fechaActual)&&date_Brasil.before(date_Abudabi))){
            nextGP=Circuito.AbuDabi();
            return Circuito.Brasil()+"\nhttp://www.eltiempo.es/sao-paulo.html";
        }else if((date_Abudabi.after(fechaActual)&&date_Abudabi.before(date_fin))){
            nextGP="Se acabo lo bueno!!! Cuenta atras!";
            return Circuito.AbuDabi()+"\nhttp://www.eltiempo.es/abu-dhabi.html";
        }
        return "";
    }

    public static String nextGPinfo(){
        date_Australia=calenda_Australia.getTime();
        date_Barein=calenda_Barein.getTime();
        date_China=calenda_China.getTime();
        date_Rusia=calenda_Rusia.getTime();
        date_España=calenda_España.getTime();
        date_Monaco=calenda_Monaco.getTime();
        date_Canada=calenda_Canada.getTime();
        date_Europa=calenda_Europa.getTime();
        date_Austria=calenda_Austria.getTime();
        date_GranBretaña=calenda_GranBretaña.getTime();
        date_Hungria=calenda_Hungria.getTime();
        date_Alemania=calenda_Alemania.getTime();
        date_Belgica=calenda_Belgica.getTime();
        date_Italia=calenda_Italia.getTime();
        date_Singapur=calenda_Singapur.getTime();
        date_Malasia=calenda_Malasia.getTime();
        date_Japon=calenda_Japon.getTime();
        date_EEUU=calenda_EEUU.getTime();
        date_Mexico=calenda_Mexico.getTime();
        date_Brasil=calenda_Brasil.getTime();
        date_Abudabi=calenda_Abudabi.getTime();
        date_fin=calenda_fin.getTime();
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistema=formateador.format(fechaActual);


        System.out.println(date_Australia.after(fechaActual)&&date_Australia.before(date_Barein));
        if (date_Australia.after(fechaActual)&&date_Australia.before(date_Barein)) {
            nextGP=Circuito.Barein();
            return nextGP;
        }else if (date_Barein.after(fechaActual)&&date_Barein.before(date_China)){
            nextGP=Circuito.China();
            return nextGP;
        }else if((date_China.after(fechaActual)&&date_China.before(date_Rusia))){
            nextGP=Circuito.Rusia();
            return nextGP;
        }else if((date_Rusia.after(fechaActual)&&date_Rusia.before(date_España))){
            nextGP=Circuito.España();
            return nextGP;
        }else if((date_España.after(fechaActual)&&date_España.before(date_Monaco))){
            nextGP=Circuito.Monaco();
            return nextGP;
        }else if((date_Monaco.after(fechaActual)&&date_Monaco.before(date_Canada))){
            nextGP=Circuito.Canada();
            return nextGP;
        }else if((date_Canada.after(fechaActual)&&date_Canada.before(date_Europa))){
            nextGP=Circuito.Europa();
            return nextGP;
        }else if((date_Europa.after(fechaActual)&&date_Europa.before(date_Austria))){
            nextGP=Circuito.Austria();
            return nextGP;
        }else if((date_Austria.after(fechaActual)&&date_Austria.before(date_GranBretaña))){
            nextGP=Circuito.GranBretaña();
            return nextGP;
        }else if((date_GranBretaña.after(fechaActual)&&date_GranBretaña.before(date_Hungria))){
            nextGP=Circuito.Hungria();
            return nextGP;
        }else if((date_Hungria.after(fechaActual)&&date_Hungria.before(date_Alemania))){
            nextGP=Circuito.Alemania();
            return nextGP;
        }else if((date_Alemania.after(fechaActual)&&date_Alemania.before(date_Belgica))){
            nextGP=Circuito.Belgica();
            return nextGP;
        }else if((date_Belgica.after(fechaActual)&&date_Belgica.before(date_Italia))){
            nextGP=Circuito.Italia();
            return nextGP;
        }else if((date_Italia.after(fechaActual)&&date_Italia.before(date_Singapur))){
            nextGP=Circuito.Singapur();
            return nextGP;
        }else if((date_Singapur.after(fechaActual)&&date_Singapur.before(date_Malasia))){
            nextGP=Circuito.Malasia();
            return nextGP;
        }else if((date_Malasia.after(fechaActual)&&date_Malasia.before(date_Japon))){
            nextGP=Circuito.Japon();
            return nextGP;
        }else if((date_Japon.after(fechaActual)&&date_Japon.before(date_EEUU))){
            nextGP=Circuito.EEUU();
            return nextGP;
        }else if((date_EEUU.after(fechaActual)&&date_EEUU.before(date_Mexico))){
            nextGP=Circuito.Mexico();
            return nextGP;
        }else if((date_Mexico.after(fechaActual)&&date_Mexico.before(date_Brasil))){
            nextGP=Circuito.Brasil();
            return nextGP;
        }else if((date_Brasil.after(fechaActual)&&date_Brasil.before(date_Abudabi))){
            nextGP=Circuito.AbuDabi();
            return nextGP;
        }else if((date_Abudabi.after(fechaActual)&&date_Abudabi.before(date_fin))){
            nextGP="Se acabo lo bueno!!! Cuenta atras!";
            return nextGP;
        }
        return "";
    }
}
