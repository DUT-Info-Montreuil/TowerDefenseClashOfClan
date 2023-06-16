//package fr.iut.montreuil.projetFinal.modele;
//
//public class ArcX extends Tour {
//    public ArcX (double x,double y, Environnement env){
//        super("ArcX",x,y,env,150,150,20);
//    }
//
//    @Override
//    public void tir() {
//        Ennemi e = this.essaieTir();
//        System.out.println("action tir" );
//        if (e != null){
//            if (e.estVivant()) {
//                Projectile p = new FlecheArcX(this.getX(), this.getY(), e, env);
//                env.ajouterProjectile(p);
//                System.out.println("pv ennemi : " + e.getPvProperty());
//            }
//        }
//    }
//}
