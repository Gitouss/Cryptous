package com.doplgangr.secrecy.events;


public class BackingUpFileEvent {
    private final String folderToBackup;
    private final String fileInBackup;

    public BackingUpFileEvent(String folderToBackup, String fileInBackup) {
        this.folderToBackup = folderToBackup;
        this.fileInBackup = fileInBackup;
    }
}
