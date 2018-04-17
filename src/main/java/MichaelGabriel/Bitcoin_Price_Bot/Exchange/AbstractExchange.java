package MichaelGabriel.Bitcoin_Price_Bot.Exchange;

abstract class AbstractExchange implements ExchangeI {

    <E extends Enum<E> & SupportedCurrencies> String getValidSymbol(String coinName, Class<E> supportedCurrencies){
        try {
            return Enum.valueOf(supportedCurrencies, coinName.toUpperCase()).getSymbol();
        }
        catch(Exception e){
            System.out.println("Coin " + coinName + " currently not supported by " + this.getClass().getName());
            return null;
        }
    }



}
