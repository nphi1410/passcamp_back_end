/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.util;

/**
 *
 * @author AD
 */
public class Generate {
    
    private Generate(){};
    
     public static String getId(String prefix, String lastId) {

        if (lastId == null) {
            return prefix + "00000001"; // First account
        }

        // Extract numeric part, increment it, and format back to string
        int numericPart = Integer.parseInt(lastId.substring(2)) + 1;
        return prefix + String.format("%08d", numericPart);
    }
     
     public static String getVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // Generates a 6-digit code
    }
}
