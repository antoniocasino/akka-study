package it.tonic.fullapprxjava.model;

public class CoinBaseResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public class Data{
        private String base;
        private String currency;
        private String amount;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
