/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSocket;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author rizky.aditya
 */
public class TestMasBro {
    public static void main(String[] args) {
        BigDecimal nominal=BigDecimal.valueOf(15678987);
        BigDecimal kurs=BigDecimal.valueOf(13827);
        BigDecimal result=nominal.divide(kurs, 11, RoundingMode.DOWN);
        System.out.println("RESULT: "+result);
        nominal=result.multiply(kurs).round(MathContext.DECIMAL64);
        System.out.println("RESULT: "+nominal);
    }
}
