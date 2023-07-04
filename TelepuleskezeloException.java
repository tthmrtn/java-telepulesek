public class TelepuleskezeloException extends Exception{
    private Telepules okozo;
    public TelepuleskezeloException(Telepules melyik, String str){
        super(str);
        this.okozo = melyik;
    }

    public Telepules getOkozo() {
        return okozo;
    }
}
