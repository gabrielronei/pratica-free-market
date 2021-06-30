package br.com.gabriels.praticafreemarket.produto;

import br.com.gabriels.praticafreemarket.produto.foto.*;

import javax.validation.constraints.Size;
import java.util.*;

import static io.jsonwebtoken.lang.Assert.*;

public class NovaImagemRequest {

    @Size(min = 1)
    private Set<String> fotos = new HashSet<>();

    public void setFotos(Set<String> fotos) {
        this.fotos = fotos;
    }

    public Set<String> getFotos() {
        return fotos;
    }

    public Set<Foto> toModel(FotoUploader fotoUploader,
                             InformacoesBasicasParaUpload informacoesBasicasParaUpload) {
        notNull(fotoUploader, "FotoUploader não podem estar nulos!");
        notNull(informacoesBasicasParaUpload, "Informações de upload não podem estar nulas!");

        return fotoUploader.upload(this.fotos, informacoesBasicasParaUpload);
    }
}
