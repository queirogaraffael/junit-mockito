package org.example.services;


import org.example.entities.Usuario;

public class SPCServiceFake implements SPCService {

    @Override
    public boolean possuiNegativacao(Usuario usuario) {
        return false;
    }
}
