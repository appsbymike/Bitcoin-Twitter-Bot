package MichaelGabriel.Bitcoin_Price_Bot.Exchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class GDAX extends AbstractExchange {

    public enum supportedCurrencies implements SupportedCurrencies {
        BITCOIN("BTC"),
        LITECOIN("LTC"),
        ETHERIUM("ETH");

        String symbol;

        supportedCurrencies(String symbol){
            this.symbol = symbol;
        }

        public String getSymbol(){
            return this.symbol;
        }
    }

    @Override
    public String getCoinPrice(String coinName) {
        String symbol = getValidSymbol(coinName, supportedCurrencies.class);
        return getPrice(symbol);
    }

    private String getPrice(String symbol){
        String address = "https://api.gdax.com/products/" + symbol + "-USD/ticker";
        try {
            URL url = new URL(address);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            urlConnection.connect();
            InputStream stream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsonObject = new JSONObject(result.toString());
            Double price = jsonObject.getDouble("price");
            return String.format("$%1$,.2f", price);
        }
        catch (Exception e) {
            return null;
        }
    }
}
