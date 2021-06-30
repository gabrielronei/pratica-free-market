package br.com.gabriels.praticafreemarket.produto.foto;

import org.slf4j.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static io.jsonwebtoken.lang.Assert.*;

@Service
class FakeFotoUploader implements FotoUploader {
    private static final String BUCKET_URL = "https://pratica-free-market.s3.amazonaws.com/produtos/%s/%s-%s.png";

    private static final Logger logger = LoggerFactory.getLogger(FakeFotoUploader.class);

    @Override
    public Set<Foto> upload(Set<String> fotos, InformacoesBasicasParaUpload informacoesUpload) {
        notEmpty(fotos, "Fotos não podem estar nulas!");
        notNull(informacoesUpload, "Informações de upload não podem estar nulas!");

        return fotos.stream().map(foto -> store(informacoesUpload))
                .map(Foto::new)
                .collect(Collectors.toSet());
    }

    private String store(InformacoesBasicasParaUpload informacoesBasicas) {
        notNull(informacoesBasicas, "Informações de upload não podem estar nulas!");
        //faz o upload => devolve o nome
        String url = BUCKET_URL.formatted(informacoesBasicas.nomeUsuario(), informacoesBasicas.getSlug(), UUID.randomUUID());
        logger.info("[UPLOAD IMAGEM] Upload da imagem realizado com sucesso! URL: {}", url);
        return url;
    }
}
