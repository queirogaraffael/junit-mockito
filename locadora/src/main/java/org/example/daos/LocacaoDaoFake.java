package org.example.daos;

import org.example.entities.Locacao;

import java.util.Collections;
import java.util.List;

public class LocacaoDaoFake implements LocacaoDao {

    @Override
    public void salvar(Locacao locacao) {

    }

    @Override
    public List<Locacao> obterLocacoesPendentes() {
        return Collections.emptyList();
    }
}
