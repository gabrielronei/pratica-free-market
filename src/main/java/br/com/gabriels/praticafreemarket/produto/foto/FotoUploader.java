package br.com.gabriels.praticafreemarket.produto.foto;

import java.util.Set;

public interface FotoUploader {
    Set<Foto> upload(Set<String> fotos, InformacoesBasicasParaUpload informacoesUpload);
}
