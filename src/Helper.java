import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Helper {
    private static final String UTILITIES_FILE = "utilities.json";
    private static int acctNum, day;
    private static String username, password;

//    public static CheckingAccount getCheckingAccount() {
//        CheckingAccount acc = new CheckingAccount();
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject jsonObject = (JSONObject)obj;
//
//            JSONObject holder = (JSONObject) jsonObject.get("checkingAccount");
//            acc.setBalance((long)holder.get("balance"));
//            acc.setAmountWithdrawn(((long)holder.get("amountWithdrawn")));
//            acc.setAmountDepo(((long)holder.get("amountDepo")));
//        } catch(Exception e) {
//            //e.printStackTrace();
//            return null;
//        }
//
//        return acc;
//    }

//    public static void setCheckingAccount(CheckingAccount acc) {
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject database = (JSONObject)obj;
//
//            JSONObject writeData = new JSONObject();
//            writeData.put("balance", acc.getBalance());
//            writeData.put("amountWithdrawn", acc.getAmountWithdrawn());
//            writeData.put("amountDepo", acc.getAmountDepo());
//
//            database.put("checkingAccount", writeData);
//
//            try (FileWriter file = new FileWriter("./src/Lab6_SE317/utilities.json")) {
//                file.write(database.toJSONString());
//                file.flush();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static SavingsAccount getSavingsAccount() {
//        SavingsAccount acc = new SavingsAccount();
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject jsonObject = (JSONObject)obj;
//
//            JSONObject holder = (JSONObject) jsonObject.get("savingsAccount");
//            acc.setBalance((long)holder.get("balance"));
//            acc.setAmountWithdrawn(((long)holder.get("amountWithdrawn")));
//            acc.setAmountDepo(((long)holder.get("amountDepo")));
//            acc.setAmountTransferred((long)holder.get("amountTransfer"));
//        } catch(Exception e) {
//            return null;
//            //e.printStackTrace();
//        }
//
//        return acc;
//    }

//    public static void setSavingsAccount(SavingsAccount acc) {
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject database = (JSONObject)obj;
//
//            JSONObject writeData = new JSONObject();
//            writeData.put("balance", acc.getBalance());
//            writeData.put("amountWithdrawn", acc.getAmountWithdrawn());
//            writeData.put("amountDepo", acc.getAmountDepo());
//            writeData.put("amountTransfer", acc.getAmountTransferred());
//
//            database.put("savingsAccount", writeData);
//
//            try (FileWriter file = new FileWriter("./src/Lab6_SE317/utilities.json")) {
//                file.write(database.toJSONString());
//                file.flush();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static UtilityAccount makeUtilityAccount(String username, String password){
//        return new UtilityAccount(username, password);
//    }

//    public static UtilityAccount getUtilityAccount() {
//        if(utilityAccountExists()) {
//            return new UtilityAccount();
//        }else {
//            return null;
//        }
//    }

//    public static boolean utilityAccountExists(){
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject jsonObject = (JSONObject)obj;
//
//            JSONObject holder = (JSONObject) jsonObject.get("utilityAccount");
//            long accountNumber = (long)holder.get("accNum");
//            String username = (String)holder.get("username");
//            String password = (String)holder.get("password");
//            return true;
//        } catch(Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


//    public static void setUtilityAccount(UtilityAccount acc) {
//        acc.setAccount();
//    }

    public static int getDay() {
        int day = 0;
        try {

            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            Long dayLong = (Long)jsonObject.get("days");
            day = dayLong.intValue();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return day;
    }

    public static void setDay(int day) {

        try {
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);


            jsonObject.put("days", day);


            try (FileWriter file = new FileWriter(UTILITIES_FILE)) {
                file.write(prettyPrintJson(jsonObject));
                file.flush();
                file.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

//    public static Payment[] getPayments() {
//        Payment[] Payments = new Payment[4];
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject jsonObject = (JSONObject)obj;
//
//            JSONArray holder = (JSONArray) jsonObject.get("bills");
//            for (int i = 0; i < holder.size(); i++) {
//                JSONObject temp = (JSONObject)holder.get(i);
//                Payment tempPayment = new Payment();
//                tempPayment.setPayAmount((long)temp.get("amount"));
//                tempPayment.setPayDate((long)temp.get("dueDate"));
//                tempPayment.setPayStatus((boolean)temp.get("status"));
//
//                Payments[i] = tempPayment;
//            }
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return Payments;
//    }

//    public static void setPayments(Payment[] Payments) {
//        JSONParser parser = new JSONParser();
//
//        try {
//            String filePath = new File("").getAbsolutePath();
//            Object obj = parser.parse(new FileReader(filePath + "/src/Lab6_SE317/utilities.json"));
//            JSONObject database = (JSONObject)obj;
//
//            database.put("bills", Payments);
//
//            try (FileWriter file = new FileWriter("./src/Lab6_SE317/utilities.json")) {
//                file.write(database.toJSONString());
//                file.flush();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void setAccount(){

        try {
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);


            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("acctNum", acctNum);

            try (FileWriter file = new FileWriter(UTILITIES_FILE)) {
                file.write(prettyPrintJson(jsonObject));
                file.flush();
                file.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to prettily print JSON object with indentation
    public static String prettyPrintJson(JSONObject jsonObject) {
        return jsonObject.toJSONString().replace(",", ",\n")
                .replace("{", "{\n").replace("}", "\n}").replace("[", "[\n").replace("]", "\n]");
    }
}
