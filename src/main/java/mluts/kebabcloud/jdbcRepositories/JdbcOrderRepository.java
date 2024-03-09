package mluts.kebabcloud.jdbcRepositories;


import mluts.kebabcloud.domain.Ingredient;
import mluts.kebabcloud.domain.IngredientRef;
import mluts.kebabcloud.domain.Kebab;
import mluts.kebabcloud.domain.KebabOrder;
import mluts.kebabcloud.jdbcRepositoryInterfaces.OrderRepository;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public KebabOrder save(KebabOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Kebab_Order (delivery_name, delivery_street, delivery_city, delivery_country, delivery_postal_code, cc_number, cc_expiration, cc_cvv, placed_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryCountry(),
                        order.getDeliveryPostalCode(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Kebab> kebabs = order.getKebabs();
        int i = 0;
        for (Kebab kebab : kebabs){
            saveKebab(orderId, i++, kebab);
        }
        return order;
    }

    private long saveKebab(Long orderId, int orderKey, Kebab kebab){
        kebab.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Kebab (name, created_at, kebab_order, kebab_order_key) values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(kebab.getName(), kebab.getCreatedAt(), orderId, orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long kebabId = keyHolder.getKey().longValue();
        kebab.setId(kebabId);

        saveIngredientRefs(kebabId, kebab.getIngredients());

        return kebabId;
    }

    private void saveIngredientRefs(long kebabId, List<Ingredient> ingredients){
        List<IngredientRef> ingredientRefs = new ArrayList<IngredientRef>();
        for(Ingredient ing : ingredients){
            ingredientRefs.add(new IngredientRef(ing.getId()));
        }

        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs){
            jdbcOperations.update("insert into Ingredient_Ref (ingredient, kebab, kebab_key) values (?, ?, ?)",
                    ingredientRef.getIngredient(), kebabId, key++);
        }
    }
}
