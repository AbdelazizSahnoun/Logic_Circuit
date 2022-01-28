package circuitDesignerPackage.Commandes;

public class QuitterApp implements Command{
    @Override
    public void execute() {
        System.exit(0);
    }
}
