package com.doplgangr.secrecy.events;

import com.doplgangr.secrecy.filesystem.encryption.Vault;


public class AddingFileDoneEvent {
    public final Vault vault;    //Path of the vault file is added

    public AddingFileDoneEvent(Vault vault) {
        this.vault = vault;
    }
}