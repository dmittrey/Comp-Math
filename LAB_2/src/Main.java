import ru.ifmo.se.pokemon.*;

public class Main {

    public static void main(String[] args) {
        Battle dom = new Battle();
        dom.addAlly(new Regirock());
        dom.addAlly(new Eevee());
        dom.addAlly(new Espeon());
        dom.addFoe(new Budew());
        dom.addFoe(new Roselia());
        dom.addFoe(new Roserade());
        dom.go();
    }
}
