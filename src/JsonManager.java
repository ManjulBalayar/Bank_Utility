import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private static final String BANKING_FILE = "banking.json";
    private JSONParser parser = new JSONParser();

    public Account[] readAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (FileReader reader = new FileReader(BANKING_FILE)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray accountsArray = (JSONArray) jsonObject.get("accounts");
            for (Object obj : accountsArray) {
                JSONObject accountObject = (JSONObject) obj;
                Account account = createAccountFromJson(accountObject);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts.toArray(new Account[0]);
    }

    private Account createAccountFromJson(JSONObject accountObject) {
        String type = (String) accountObject.get("type");
        String accountId = (String) accountObject.get("accountId");
        double balance = (Double) accountObject.get("balance");

        if ("CheckingAccount".equals(type)) {
            double dailyWithdrawn = (Double) accountObject.get("dailyWithdrawn");
            double dailyDeposited = (Double) accountObject.get("dailyDeposited");
            CheckingAccount ca = new CheckingAccount(accountId, balance);
            ca.setDailyWithdrawn(dailyWithdrawn);
            ca.setDailyDeposited(dailyDeposited);
            return ca;
        } else if ("SavingsAccount".equals(type)) {
            double dailyTransferred = (Double) accountObject.get("dailyTransferred");
            SavingsAccount sa = new SavingsAccount(accountId, balance);
            sa.setDailyTransferred(dailyTransferred);
            return sa;
        }
        return null; // or handle more appropriately
    }

    public void writeAccounts(Account[] accounts) {
        JSONObject rootObject = new JSONObject();
        JSONArray accountsArray = new JSONArray();
        for (Account account : accounts) {
            accountsArray.add(convertAccountToJson(account));
        }
        rootObject.put("accounts", accountsArray);

        try (FileWriter writer = new FileWriter(BANKING_FILE)) {
            writer.write(rootObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject convertAccountToJson(Account account) {
        JSONObject accountObject = new JSONObject();
        accountObject.put("type", account.getClass().getSimpleName());
        accountObject.put("accountId", account.getAccountId());
        accountObject.put("balance", account.getBalance());
        if (account instanceof CheckingAccount) {
            CheckingAccount ca = (CheckingAccount) account;
            accountObject.put("dailyWithdrawn", ca.getDailyWithdrawn());
            accountObject.put("dailyDeposited", ca.getDailyDeposited());
        } else if (account instanceof SavingsAccount) {
            SavingsAccount sa = (SavingsAccount) account;
            accountObject.put("dailyTransferred", sa.getDailyTransferred());
        }
        return accountObject;
    }
}
