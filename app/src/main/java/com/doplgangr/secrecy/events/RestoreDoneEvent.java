package com.doplgangr.secrecy.events;

import java.io.File;


public class RestoreDoneEvent {
    public final File backupFile; //Path of backupFile

    public RestoreDoneEvent(File backupFile) {
        this.backupFile = backupFile;
    }
}
