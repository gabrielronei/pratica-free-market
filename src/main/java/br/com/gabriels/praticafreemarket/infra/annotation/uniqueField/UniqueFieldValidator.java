package br.com.gabriels.praticafreemarket.infra.annotation.uniqueField;

import javax.persistence.*;
import javax.validation.*;
import java.util.List;

import static org.springframework.util.Assert.state;

public final class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {
    private String atributoDominio;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.atributoDominio = constraintAnnotation.nomeCampo();
        this.clazz = constraintAnnotation.classeDominio();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " + clazz.getName() + " where " + atributoDominio + "=:value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        state(list.size() <= 1, "Foi encontrado mais de um " + clazz + " com o atributo " + atributoDominio + " = " + value);

        return list.isEmpty();
    }
}
