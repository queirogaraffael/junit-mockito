package org.example.daos;

import org.example.entities.Locacao;

import java.util.List;

public interface LocacaoDao {
    public void salvar(Locacao locacao);

    public List<Locacao> obterLocacoesPendentes();
}
