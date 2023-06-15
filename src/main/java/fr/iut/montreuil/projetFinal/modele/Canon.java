package fr.iut.montreuil.projetFinal.modele;

public class Canon extends Tour {

    public Canon (double x,double y, Environnement env){
        super("Canon",x,y,env,75,50,45);

    }

    @Override
    public void tir() {
        Ennemi e = this.essaieTir();
        System.out.println("action tir" );
        if (e != null){
            if (e.estVivant()) {
                Projectile p = new Boulet(this.getX(), this.getY(), e, env);
                env.ajouterProjectile(p);
                System.out.println("pv ennemi : " + e.getPv());
            }
        }
    }
}


