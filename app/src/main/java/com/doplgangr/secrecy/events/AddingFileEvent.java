package com.doplgangr.secrecy.events;

import com.doplgangr.secrecy.filesystem.encryption.Vault;


public class AddingFileEvent {
    public final Vault vaultToAdd;
    public final String fileToAdd;

    public AddingFileEvent(Vault vaultToAdd, String fileToAdd) {
        this.vaultToAdd = vaultToAdd;
        this.fileToAdd = fileToAdd;
    }
}
