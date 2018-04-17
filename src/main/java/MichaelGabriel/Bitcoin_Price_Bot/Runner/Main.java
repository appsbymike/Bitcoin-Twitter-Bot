package MichaelGabriel.Bitcoin_Price_Bot.Runner;

import MichaelGabriel.Bitcoin_Price_Bot.Exchange.Binance;
import MichaelGabriel.Bitcoin_Price_Bot.Exchange.ExchangeI;
import MichaelGabriel.Bitcoin_Price_Bot.Exchange.Exchanges;
import MichaelGabriel.Bitcoin_Price_Bot.Exchange.GDAX;
import MichaelGabriel.Bitcoin_Price_Bot.Twitter.MyTwitter;

public class Main {

	public static void main(String[] args) {		
		boolean cancel = false;
		while(!cancel) {
			ExchangeI gdax = Exchanges.GDAX.getExchange();
			ExchangeI binance = Exchanges.BINANCE.getExchange();

			String gbtc, gltc, bbtc, bltc;
			gbtc = gdax.getCoinPrice("bitcoin");
			gltc = gdax.getCoinPrice("litecoin");
			bbtc = binance.getCoinPrice("bitcoin");
			bltc = binance.getCoinPrice("litecoin");

			try {
				if(gbtc != null && gltc != null && bbtc != null && bltc != null) {
					String message = ("Current #BTC Price:" + "\nGDAX : " + gbtc + " | Binance : " + bbtc + "\n\nCurrent #LTC Price: " + "\nGDAX : " + gltc+ " | Binance : " + bltc + "\n#Bitcoin #Litecoin");
					MyTwitter myTwitter = new MyTwitter();
					myTwitter.login();
					myTwitter.tweet(message);
				}
				
				Thread.sleep(600000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				cancel = true;
			}
		}
	}

}
