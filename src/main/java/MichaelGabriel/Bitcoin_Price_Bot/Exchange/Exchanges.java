package MichaelGabriel.Bitcoin_Price_Bot.Exchange;

public enum Exchanges {
    BINANCE(new Binance()),
    COINDESK(new CoinDesk()),
    GDAX(new GDAX());

    ExchangeI exchange;

    Exchanges(ExchangeI exchange){
        this.exchange = exchange;
    }

    public ExchangeI getExchange(){
        return this.exchange;
    }
}
