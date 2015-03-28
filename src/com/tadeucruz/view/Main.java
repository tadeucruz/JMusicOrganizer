package com.tadeucruz.view;

import com.tadeucruz.control.MainControler;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando organização da musica");
        MainControler mc = new MainControler();
        mc.organizeMusic("/mnt/dados/MusicaOld/", "/mnt/dados/Musica/");
        System.out.println("Teminou a organização da musica");
    }
}
