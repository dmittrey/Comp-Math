import ru.ifmo.se.pokemon.*;


class Superpower extends PhysicalMove{
    protected Superpower() {
        super(Type.FIGHTING, 120, 100);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.ATTACK, -1);
        p.setMod(Stat.DEFENSE, -1);
    }
    @Override
    protected String describe() {
        return "Теряет 6 здоровья"
    }
}
