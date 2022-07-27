package com.MoreiraJunior.desafio;

import com.MoreiraJunior.desafio.watcher.WatchFile;

public class Main {

    public static void main(String[] args) {
        WatchFile.start();
        WatchFile.watch();
    }
}
