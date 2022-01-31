public class MotCleIdentificateur {
    private String motCleReserve;

    public MotCleIdentificateur(String motCleReserve){
        motCleReserve = motCleReserve.toLowerCase();
        this.motCleReserve = motCleReserve;
    }
}
